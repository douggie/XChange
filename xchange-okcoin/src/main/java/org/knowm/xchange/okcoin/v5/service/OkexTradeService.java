package org.knowm.xchange.okcoin.v5.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.trade.UserTrades;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.okcoin.OkexAdaptersV5;
import org.knowm.xchange.okcoin.OkexExchangeV5;
import org.knowm.xchange.okcoin.v3.dto.trade.OkexTransaction;
import org.knowm.xchange.okcoin.v5.dto.trade.OkexOpenOrder;
import org.knowm.xchange.okcoin.v5.dto.trade.OkexOrderFlags;
import org.knowm.xchange.okcoin.v5.dto.trade.OkexTradeHistoryParams;
import org.knowm.xchange.okcoin.v5.dto.trade.OrderCancellationRequest;
import org.knowm.xchange.okcoin.v5.dto.trade.OrderPlacementType;
import org.knowm.xchange.service.trade.TradeService;
import org.knowm.xchange.service.trade.params.CancelOrderByCurrencyPair;
import org.knowm.xchange.service.trade.params.CancelOrderByIdParams;
import org.knowm.xchange.service.trade.params.CancelOrderParams;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;
import org.knowm.xchange.service.trade.params.orders.DefaultOpenOrdersParamCurrencyPair;
import org.knowm.xchange.service.trade.params.orders.OpenOrdersParamCurrencyPair;
import org.knowm.xchange.service.trade.params.orders.OpenOrdersParams;

public class OkexTradeService extends OkexTradeServiceRaw implements TradeService {

  private static final int orders_limit = 100;
  private static final int transactions_limit = 100;

  public OkexTradeService(OkexExchangeV5 exchange) {
    super(exchange);
  }

  @Override
  public String placeLimitOrder(LimitOrder o) throws IOException {

    // 0: Normal limit order (Unfilled and 0 represent normal limit order) 1: Post only 2: Fill Or
    // Kill 3: Immediatel Or Cancel
    OrderPlacementType orderType =
        o.hasFlag(OkexOrderFlags.POST_ONLY)
            ? OrderPlacementType.post_only
            : OrderPlacementType.normal;

    return null;
  }

  @Override
  public boolean cancelOrder(String orderId) throws IOException {
    throw new NotAvailableFromExchangeException();
  }

  @Override
  public boolean cancelOrder(CancelOrderParams orderParams) throws IOException {
    if (!(orderParams instanceof CancelOrderByIdParams)
        || !(orderParams instanceof CancelOrderByCurrencyPair)) {
      throw new UnsupportedOperationException(
          "Cancelling an order is only available for a single market and a single id.");
    }

    String id = ((CancelOrderByIdParams) orderParams).getOrderId();
    String instrumentId =
        OkexAdaptersV5.toSpotInstrument(
            ((CancelOrderByCurrencyPair) orderParams).getCurrencyPair());

    OrderCancellationRequest req =
        OrderCancellationRequest.builder().instrumentId(instrumentId).build();
    // OrderCancellationResponse o = spotCancelOrder(id, req);
    return true;
  }

  @Override
  public OpenOrders getOpenOrders() throws IOException {
    throw new NotAvailableFromExchangeException();
  }

  @Override
  public OpenOrdersParams createOpenOrdersParams() {
    return new DefaultOpenOrdersParamCurrencyPair();
  }

  @Override
  public OpenOrders getOpenOrders(OpenOrdersParams params) throws IOException {
    if (!(params instanceof OpenOrdersParamCurrencyPair)) {
      throw new UnsupportedOperationException(
          "Getting open orders is only available for a single market.");
    }
    return null;
  }

  @Override
  public TradeHistoryParams createTradeHistoryParams() {
    return new OkexTradeHistoryParams();
  }

  @Override
  public UserTrades getTradeHistory(TradeHistoryParams params) throws IOException {
    throw new NotYetImplementedForExchangeException();
  }

  public UserTrades getMarginTradeHistory(TradeHistoryParams params) throws IOException {
    throw new NotYetImplementedForExchangeException();
  }

  private static BigDecimal stripTrailingZeros(BigDecimal bd) {
    bd = bd.stripTrailingZeros();
    bd = bd.setScale(Math.max(bd.scale(), 0));
    return bd;
  }

  private List<OkexTransaction> fetchTradesForOrder(OkexOpenOrder o) throws IOException {
    throw new NotYetImplementedForExchangeException();
  }

  private List<OkexTransaction> fetchMarginTradesForOrder(OkexOpenOrder o) throws IOException {
    throw new NotYetImplementedForExchangeException();
  }
}

package org.knowm.xchange.examples.dsx.trade;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dsx.DSXExchangeV3;
import org.knowm.xchange.dsx.dto.trade.DSXTradeHistoryResult;
import org.knowm.xchange.dsx.service.DSXTradeServiceV3;
import org.knowm.xchange.dsx.service.trade.params.DSXTradeHistoryParams;
import org.knowm.xchange.dto.trade.UserTrades;
import org.knowm.xchange.examples.dsx.DSXExamplesUtils;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.service.trade.TradeService;

import java.io.IOException;
import java.util.Map;

/** @author Mikhail Wall */
public class DSXUserTradeHistoryDemoV3 {

  public static void main(String[] args) throws IOException {
    Exchange dsx = DSXExamplesUtils.createExchange(DSXExchangeV3.class);
    generic(dsx);
    raw(dsx);
  }

  private static void generic(Exchange exchange) throws IOException {

    TradeService tradeService = exchange.getTradeService();
    try {
      DSXTradeHistoryParams params = new DSXTradeHistoryParams();
      params.setCurrencyPair(CurrencyPair.BTC_USD);
      UserTrades trades = tradeService.getTradeHistory(params);

      System.out.println(trades.toString());
    } catch (ExchangeException e) {
      System.out.println(e.getMessage());
    }
  }

  private static void raw(Exchange exchange) throws IOException {
    DSXTradeServiceV3 tradeService = (DSXTradeServiceV3) exchange.getTradeService();
    Map<Long, DSXTradeHistoryResult> trades = null;
    try {
      trades = tradeService.getDSXTradeHistory(null, null, null, null, null, null, null);
      for (Map.Entry<Long, DSXTradeHistoryResult> entry : trades.entrySet()) {
        System.out.println("ID: " + entry.getKey() + ", Trade:" + entry.getValue());
      }
      System.out.println(trades.toString());
    } catch (ExchangeException e) {
      System.out.println(e.getMessage());
    }
  }
}

package info.bitrich.xchangestream.dydx.dto.v3;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.bitrich.xchangestream.dydx.dto.dydxWebSocketTransaction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.trade.LimitOrder;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** Author: Max Gao (gaamox@tutanota.com) Created: 08-03-2021 */
@NoArgsConstructor
@Getter
@Setter
public class dydxUpdateOrderBookMessage extends dydxWebSocketTransaction {
  @JsonProperty("contents")
  private Contents contents;

  @Getter
  @Setter
  public static class Contents {
    @JsonProperty("offset")
    private String offset;

    @JsonProperty("bids")
    private String bids[][];

    @JsonProperty("asks")
    private String asks[][];
  }

  /** Adapted from coinbaseProOrderBookChanges */
  protected List<LimitOrder> dydxOrderBookChanges(
      org.knowm.xchange.dto.Order.OrderType orderType,
      CurrencyPair currencyPair,
      String[][] changes,
      SortedMap<BigDecimal, BigDecimal> sideEntries,
      int maxDepth) {

    if (sideEntries == null) {
      return Collections.emptyList();
    }

    for (String[] level : changes) {
      BigDecimal price = new BigDecimal(level[level.length - 2]);
      BigDecimal volume = new BigDecimal(level[level.length - 1]);
      sideEntries.put(price, volume);
    }

    Stream<Map.Entry<BigDecimal, BigDecimal>> stream =
        sideEntries.entrySet().stream()
            .filter(level -> level.getValue().compareTo(BigDecimal.ZERO) != 0);

    if (maxDepth != 0) {
      stream = stream.limit(maxDepth);
    }

    return stream
        .map(
            level ->
                new LimitOrder(
                    orderType, level.getValue(), currencyPair, "0", null, level.getKey()))
        .collect(Collectors.toList());
  }

  public OrderBook toOrderBook(
      SortedMap<BigDecimal, BigDecimal> bids,
      SortedMap<BigDecimal, BigDecimal> asks,
      int maxDepth,
      CurrencyPair currencyPair) {
    List<LimitOrder> dydxBids =
        dydxOrderBookChanges(
            org.knowm.xchange.dto.Order.OrderType.BID,
            currencyPair,
            this.contents.getBids() != null ? this.contents.getBids() : null,
            bids,
            maxDepth);

    List<LimitOrder> dydxAsks =
        dydxOrderBookChanges(
            org.knowm.xchange.dto.Order.OrderType.ASK,
            currencyPair,
            this.contents.getAsks() != null ? this.contents.getAsks() : null,
            asks,
            maxDepth);

    return new OrderBook(null, dydxAsks, dydxBids, false);
  }
}

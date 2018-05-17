package info.bitrich.xchangestream.bitmex;


import io.reactivex.Observable;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;

import java.util.Arrays;
import java.util.stream.Collectors;


/**
 * Created by Declan
 */
public class BitmexStreamingTradeService implements StreamingTradeService {

    private final BitmexStreamingService streamingService;

    public BitmexStreamingTradeService(BitmexStreamingService streamingService) {
        this.streamingService = streamingService;
    }

    @Override
    public Observable<Order> getOrders(CurrencyPair currencyPair, Object... args) {
        String channelName = "order";
        String instrument = currencyPair.base.toString() + currencyPair.counter.toString();
        return streamingService.subscribeBitmexChannel(channelName).flatMapIterable(s -> {
            BitmexOrder[] bitmexOrders = s.toBitmexOrders();
            return Arrays.stream(bitmexOrders)
                    .filter(bitmexOrder -> bitmexOrder.symbol() == instrument)
                    .filter(not(BitmexOrder::workingIndicator))
                    .map(BitmexOrder::toOrder).collect(Collectors.toList());
        });
    }

    @Override
    public void submitOrder(Order order, CurrencyPair var1, Object... var2) {
        throw new NotYetImplementedForExchangeException();
    }
}

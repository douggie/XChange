package info.bitrich.xchangestream.service;

import io.reactivex.Completable;

/**
 * Base for all streaming services, declares connect() method including before connection logic
 */
public abstract class ConnectableService {

    /**
     * Exchange specific parameter is used for providing {@link Runnable} action which is caused before setup new connection.
     * For example adding throttle control for limiting too often opening connections:<br/>
     * <pre>
     * {@code
     * static final TimedSemaphore limiter = new TimedSemaphore(1, MINUTES, 15);
     * ExchangeSpecification spec = exchange.getDefaultExchangeSpecification();
     * spec.setExchangeSpecificParameters(ImmutableMap.of(
     *   StreamingExchange.BEFORE_CONNECTION_EVENT_HANDLER, () -> limiter.acquire()
     * ));
     * }
     * </pre>
     */
    public static final String BEFORE_CONNECTION_HANDLER = "Before_Connection_Event_Handler";

    /**
     * {@link Runnable} handler is called before opening new socket connection.
     */
    Runnable beforeConnectionHandler = () -> {
    };

    public void setBeforeConnectionHandler(Runnable beforeConnectionHandler) {
        if (beforeConnectionHandler != null) {
            this.beforeConnectionHandler = beforeConnectionHandler;
        }
    }

    protected abstract Completable openConnection();

    public Completable connect() {
        beforeConnectionHandler.run();
        return openConnection();
    }


}

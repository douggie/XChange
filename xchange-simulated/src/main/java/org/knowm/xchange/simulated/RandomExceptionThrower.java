package org.knowm.xchange.simulated;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Random;

import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.exceptions.FrequencyLimitExceededException;
import org.knowm.xchange.exceptions.NonceException;
import org.knowm.xchange.exceptions.RateLimitExceededException;
import org.knowm.xchange.exceptions.SystemOverloadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This will cause {@link SimulatedExchange} to fail 0.5% of the time with a
 * selection of commnplace transient issues which could happen at any time
 * in real life and should therefore be handled gracefully in client code.
 * Pass this to {@link ExchangeSpecification#getExchangeSpecificParametersItem(String)}
 * using the parameter name {@link SimulatedExchange#ON_OPERATION_PARAM} during
 * long-running integration testing to inject an appropriate bit of chaos into
 * proceedings.
 *
 * @author Graham Crockford
 */
public class RandomExceptionThrower implements SimulatedExchangeOperationListener {

  private static final Logger LOGGER = LoggerFactory.getLogger(MatchingEngine.class);
  private static final String GENERIC_GUIDE = "Application code should handle this gracefully.";

  private final Random random;

  /**
   * Uses a random seed derived from the system clock.
   */
  public RandomExceptionThrower() {
    this(23423212554L ^ System.nanoTime());
  }

  /**
   * Uses a specified random seed
   *
   * @param seed the random seed.
   */
  public RandomExceptionThrower(long seed) {
    this.random = new Random(seed);
    LOGGER.info("Simulated exchange will fire random transient exceptions, with random seed: {}", seed);
  }

  @Override
  public void onSimulatedExchangeOperation() throws IOException {
    int val = random.nextInt(1000);
    if (val == 1) {
      throw new NonceException("Exchanges often complain about nonce issues. " + GENERIC_GUIDE);
    } else if (val == 2) {
      throw new SocketTimeoutException("Socket timeouts connecting to exchanges are commonplace. " + GENERIC_GUIDE);
    } else if (val == 3) {
      throw new SystemOverloadException("System overloads are a common error on some exchanges. " + GENERIC_GUIDE);
    } else if (val == 4) {
      throw new RateLimitExceededException("Are you gracefully backing off when you get rate limit errors?");
    } else if (val == 5) {
      throw new FrequencyLimitExceededException("Are you gracefully backing off when you get rate limit errors?");
    }
  }
}

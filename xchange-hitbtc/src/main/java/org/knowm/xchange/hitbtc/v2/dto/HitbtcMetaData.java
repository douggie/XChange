package org.knowm.xchange.hitbtc.v2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.meta.CurrencyMetaData;
import org.knowm.xchange.dto.meta.CurrencyPairMetaData;
import org.knowm.xchange.dto.meta.ExchangeMetaData;
import org.knowm.xchange.dto.meta.FeeTier;
import org.knowm.xchange.dto.meta.RateLimit;

public class HitbtcMetaData extends ExchangeMetaData {

  @JsonProperty("min_poll_delay")
  private final int minPollDelay;

  /**
   * Constructor
   *
   * @param currencyPairs
   * @param currencies
   * @param publicRateLimits
   * @param privateRateLimits
   * @param shareRateLimits
   * @param minPollDelay
   */
  public HitbtcMetaData(
      @JsonProperty("currency_pairs") Map<CurrencyPair, CurrencyPairMetaData> currencyPairs,
      @JsonProperty("currencies") Map<Currency, CurrencyMetaData> currencies,
      @JsonProperty("public_rate_limits") RateLimit[] publicRateLimits,
      @JsonProperty("private_rate_limits") RateLimit[] privateRateLimits,
      @JsonProperty("share_rate_limits") Boolean shareRateLimits,
      @JsonProperty("min_poll_delay") int minPollDelay,
      @JsonProperty("fee_tiers") FeeTier[] feeTiers) {
    super(
        currencyPairs, currencies, publicRateLimits, privateRateLimits, shareRateLimits, feeTiers);

    this.minPollDelay = minPollDelay;
  }

  public int getMinPollDelay() {
    return minPollDelay;
  }
}

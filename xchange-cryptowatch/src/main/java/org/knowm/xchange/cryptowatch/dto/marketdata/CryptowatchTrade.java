package org.knowm.xchange.cryptowatch.dto.marketdata;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;
import java.math.BigDecimal;

@JsonDeserialize(using = CryptowatchTrade.CryptowatchTradesDeserializer.class)
public class CryptowatchTrade {

  private final long timestamp;
  private final BigDecimal price;
  private final BigDecimal Amount;

  public CryptowatchTrade(long timestamp, BigDecimal price, BigDecimal amount) {
    this.timestamp = timestamp;
    this.price = price;
    Amount = amount;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public BigDecimal getAmount() {
    return Amount;
  }

  static class CryptowatchTradesDeserializer extends JsonDeserializer<CryptowatchTrade> {

    @Override
    public CryptowatchTrade deserialize(JsonParser jsonParser, DeserializationContext ctxt)
        throws IOException {

      ObjectCodec oc = jsonParser.getCodec();
      JsonNode node = oc.readTree(jsonParser);
      if (node.isArray()) {
        long timestamp = node.path(1).asLong();
        BigDecimal price = new BigDecimal(node.path(2).asText());
        BigDecimal volume = new BigDecimal(node.path(3).asText());
        return new CryptowatchTrade(timestamp, price, volume);
      }

      return null;
    }
  }
}

package org.knowm.xchange.okcoin.v5.service;

import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.client.ExchangeRestProxyBuilder;
import org.knowm.xchange.okcoin.OkexDigestV5;
import org.knowm.xchange.okcoin.OkexExchangeV5;
import org.knowm.xchange.okcoin.OkexV5;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;

public class OkexBaseService extends BaseExchangeService<OkexExchangeV5> implements BaseService {

  protected final OkexV5 okex;
  protected final String apikey;
  protected final String passphrase;
  protected final OkexDigestV5 digest;

  protected final String tradepwd;

  public OkexBaseService(OkexExchangeV5 exchange) {
    super(exchange);
    final ExchangeSpecification spec = exchange.getExchangeSpecification();
    okex = ExchangeRestProxyBuilder.forInterface(OkexV5.class, spec).build();
    apikey = spec.getApiKey();
    passphrase = (String) spec.getExchangeSpecificParametersItem("passphrase");

    String secretKey = spec.getSecretKey();
    digest = secretKey == null ? null : new OkexDigestV5(secretKey);
    tradepwd = (String) spec.getExchangeSpecificParametersItem("tradepwd");
  }

  protected static String timestamp() {
    return System.currentTimeMillis() / 1000 + ".000"; //          <-- works as well
    // return Instant.now().toString();
  }
}

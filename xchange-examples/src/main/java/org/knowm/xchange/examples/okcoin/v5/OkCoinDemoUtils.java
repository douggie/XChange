package org.knowm.xchange.examples.okcoin.v5;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.okcoin.OkexExchangeV5;
import org.knowm.xchange.utils.AuthUtils;

public class OkCoinDemoUtils {

  public static Exchange createExchange() {
    Exchange exchange = ExchangeFactory.INSTANCE.createExchange(OkexExchangeV5.class);
    AuthUtils.setApiAndSecretKey(exchange.getExchangeSpecification(), "okcoin");
    return exchange;
  }
}

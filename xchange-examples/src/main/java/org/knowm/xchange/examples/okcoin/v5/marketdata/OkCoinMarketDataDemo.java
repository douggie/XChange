package org.knowm.xchange.examples.okcoin.v5.marketdata;

import java.io.IOException;
import java.text.ParseException;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.okcoin.OkexExchangeV5;
import org.knowm.xchange.okcoin.v5.service.OkexMarketDataService;

public class OkCoinMarketDataDemo {

  public static void main(String[] args) throws IOException, ParseException {

    Exchange okCoinExchange = ExchangeFactory.INSTANCE.createExchange(OkexExchangeV5.class);
    OkexMarketDataService marketDataService =
        (OkexMarketDataService) okCoinExchange.getMarketDataService();

    OrderBook exchangeRates = marketDataService.getOrderBook(null, args);
    System.out.println("Exchange Rates: " + exchangeRates);
  }
}

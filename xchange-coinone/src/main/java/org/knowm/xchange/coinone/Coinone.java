package org.knowm.xchange.coinone;

import java.io.IOException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import org.knowm.xchange.coinone.dto.CoinoneException;
import org.knowm.xchange.coinone.dto.marketdata.CoinoneOrderBook;
import org.knowm.xchange.coinone.dto.marketdata.CoinoneTicker;

public interface Coinone {

  @GET
  @Path("ticker")
  CoinoneTicker getTicker(@QueryParam("currency") String currency)
      throws IOException, CoinoneException;

  @GET
  @Path("orderbook")
  CoinoneOrderBook getOrderBook(@QueryParam("currency") String currency)
      throws IOException, CoinoneException;
}

package org.knowm.xchange.hitbtc.v2.internal.api;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.knowm.xchange.hitbtc.v2.dto.HitbtcSymbol;
import org.knowm.xchange.hitbtc.v2.dto.HitbtcTicker;

/**
 * Version 2 of HitBtc API. See https://api.hitbtc.com/api/2/explore/
 */
@Path("/api/2/")
public interface HitbtcApi {

  //Public API

  @GET
  @Path("public/symbol")
  List<HitbtcSymbol> getSymbols() throws IOException;

  @GET
  @Path("public/ticker/{symbol}")
  HitbtcTicker getHitbtcTicker(@PathParam("symbol") String symbol) throws IOException;




  //Trading API






}

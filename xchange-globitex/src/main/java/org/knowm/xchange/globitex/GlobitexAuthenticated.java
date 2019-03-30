package org.knowm.xchange.globitex;

import org.knowm.xchange.globitex.dto.account.GlobitexAccounts;
import org.knowm.xchange.globitex.dto.trade.GlobitexUserTrades;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.SynchronizedValueFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/api/1/")
@Produces(MediaType.APPLICATION_JSON)
public interface GlobitexAuthenticated extends Globitex{

    @GET
    @Path("payment/accounts")
    GlobitexAccounts getAccounts(
            @HeaderParam("X-API-Key") String apiKey,
            @HeaderParam("X-Nonce") SynchronizedValueFactory<Long> nonce,
            @HeaderParam("X-Signature") ParamsDigest signature
    ) throws IOException;

    @GET
    @Path("trading/trades")
    GlobitexUserTrades getTradeHistory(
            @HeaderParam("X-API-Key") String apiKey,
            @HeaderParam("X-Nonce") SynchronizedValueFactory<Long> nonce,
            @HeaderParam("X-Signature") ParamsDigest signature,
            @QueryParam("by") String sortBy,
            @QueryParam("startIndex") int startIndex,
            @QueryParam("maxResults") int limit,
            @QueryParam("symbols") String currencies,
            @QueryParam("account") String account
    ) throws IOException;

}

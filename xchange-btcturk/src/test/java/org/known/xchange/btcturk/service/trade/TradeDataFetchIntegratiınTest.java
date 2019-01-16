package org.known.xchange.btcturk.service.trade;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.btcturk.BTCTurkExchange;
import org.knowm.xchange.btcturk.dto.BTCTurkOrderTypes;
import org.knowm.xchange.btcturk.dto.account.BTCTurkUserTransactions;
import org.knowm.xchange.btcturk.dto.trade.BTCTurkExchangeResult;
import org.knowm.xchange.btcturk.dto.trade.BTCTurkOpenOrders;
import org.knowm.xchange.btcturk.service.BTCTurkTradeService;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.service.trade.TradeService;
import org.known.xchange.btcturk.service.BTCTurkDemoUtilsTest;

/** @author mertguner */
public class TradeDataFetchIntegratiınTest {

	private Exchange btcTurk;
	private BTCTurkTradeService btcTurkTradeService;
	private TradeService tradeService;
	
	@Before
	public void InitExchange() throws IOException 
	{
		if(BTCTurkDemoUtilsTest.BTCTURK_APIKEY.isEmpty())
			btcTurk = ExchangeFactory.INSTANCE.createExchange(BTCTurkExchange.class.getName());
		else
		{
			ExchangeSpecification exSpec = new BTCTurkExchange().getDefaultExchangeSpecification();
    		exSpec.setApiKey(BTCTurkDemoUtilsTest.BTCTURK_APIKEY);
    		exSpec.setSecretKey(BTCTurkDemoUtilsTest.BTCTURK_SECRETKEY); 
    		btcTurk = ExchangeFactory.INSTANCE.createExchange(exSpec);
		}
    	
    	tradeService = btcTurk.getTradeService();
    	btcTurkTradeService = (BTCTurkTradeService)tradeService;
	}
	
	@Test
	  public void testplaceOrderAndOpenOrders() throws IOException {

		if(tradeService != null)
		{
			List<BTCTurkOpenOrders> openOrders = btcTurkTradeService.getBTCTurkOpenOrders(CurrencyPair.ETH_TRY);

			 Boolean result = false;
			 if(openOrders.isEmpty())
			 {
				BTCTurkExchangeResult exchangeResult = btcTurkTradeService.placeLimitOrder(new BigDecimal("0.01"), new BigDecimal(713), CurrencyPair.ETH_TRY, BTCTurkOrderTypes.Sell);
				result = btcTurkTradeService.cancelOrder(exchangeResult.getId());
			 }
			 else 
			 {
				 result = btcTurkTradeService.cancelOrder(openOrders.get(0).getId());			 
			 }
			 assertThat(result).isEqualTo(true);			 
		}else
			assertThat(tradeService).isEqualTo(null);	
	}
	
	@Test
	  public void testUserTransactions() throws IOException {

		if(tradeService != null)
		{
			List<BTCTurkUserTransactions> userTransactions = btcTurkTradeService.getBTCTurkUserTransactions();
			assertThat(userTransactions.size()).isEqualTo(25);			
		}else
			assertThat(tradeService).isEqualTo(null);
	}
}

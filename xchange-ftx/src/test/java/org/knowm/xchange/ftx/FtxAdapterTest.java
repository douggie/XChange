package org.knowm.xchange.ftx;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.knowm.xchange.ftx.dto.FtxResponse;
import org.knowm.xchange.ftx.dto.trade.FtxOrderDto;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FtxAdapterTest {

    @Test
    public void adaptOpenOrdersTest() throws IOException {
        InputStream is =
                FtxAdapterTest.class.getResourceAsStream(
                        "/responses/example-ftxOpenOrders.json");

        // Use Jackson to parse it
        ObjectMapper mapper = new ObjectMapper();
        FtxResponse<List<FtxOrderDto>> ftxResponse = mapper.readValue(is, new TypeReference<FtxResponse<List<FtxOrderDto>>>() {});

        assertThat(FtxAdapters.adaptOpenOrders(ftxResponse).getOpenOrders().size()).isEqualTo(1);
    }

    @Test
    public void adaptUserTradesTest() throws IOException{
        InputStream is =
                FtxAdapterTest.class.getResourceAsStream(
                        "/responses/example-ftxOrderHistory.json");

        // Use Jackson to parse it
        ObjectMapper mapper = new ObjectMapper();
        FtxResponse<List<FtxOrderDto>> ftxResponse = mapper.readValue(is, new TypeReference<FtxResponse<List<FtxOrderDto>>>() {});

        assertThat(FtxAdapters.adaptOpenOrders(ftxResponse).getOpenOrders().size()).isEqualTo(1);
    }
}

package org.knowm.xchange.okcoin.v5.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class FuturesOpenOrdersResponse extends OkexResponse {

  @JsonProperty("order_info")
  private List<OkexFuturesOpenOrder> orders;
}

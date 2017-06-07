package org.knowm.xchange.dsx.service.trade.params;

import java.util.Date;

import org.knowm.xchange.dsx.DSXAuthenticated;
import org.knowm.xchange.service.trade.params.DefaultTradeHistoryParamPaging;
import org.knowm.xchange.service.trade.params.TradeHistoryParamsIdSpan;
import org.knowm.xchange.service.trade.params.TradeHistoryParamsTimeSpan;

/**
 * @author Mikhail Wall
 */

public class DSXTransHistoryParams extends DefaultTradeHistoryParamPaging implements TradeHistoryParamsIdSpan, TradeHistoryParamsTimeSpan {

  private DSXAuthenticated.SortOrder sortOrder;
  private String startId;
  private String endId;
  private Date startTime;
  private Date endTime;

  @Override
  public void setStartId(String startId) {

    this.startId = startId;
  }

  @Override
  public String getStartId() {

    return startId;
  }

  @Override
  public void setEndId(String endId) {

    this.endId = endId;
  }

  @Override
  public String getEndId() {

    return endId;
  }

  @Override
  public void setStartTime(Date startTime) {

    this.startTime = startTime;
  }

  @Override
  public Date getStartTime() {

    return startTime;
  }

  @Override
  public void setEndTime(Date endTime) {

    this.endTime = endTime;
  }

  @Override
  public Date getEndTime() {

    return endTime;
  }

  public void setSortOrder(DSXAuthenticated.SortOrder sortOrder) {

    this.sortOrder = sortOrder;
  }

  public DSXAuthenticated.SortOrder getSortOrder() {

    return sortOrder;
  }
}

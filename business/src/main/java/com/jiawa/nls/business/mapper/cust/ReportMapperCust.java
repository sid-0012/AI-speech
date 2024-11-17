package com.jiawa.nls.business.mapper.cust;

import com.jiawa.nls.business.resp.StatisticDateResp;

import java.math.BigDecimal;
import java.util.List;

public interface ReportMapperCust {

    Integer queryOnlineCount();

    Integer queryRegisterCount();

    Integer queryFiletransCount();

    Integer queryFiletransSecond();

    Integer queryOrderCount();

    BigDecimal queryOrderAmount();

    List<StatisticDateResp> query30RegisterCount();

    List<StatisticDateResp> query30FiletransCount();

    List<StatisticDateResp> query30FiletransSecond();

    List<StatisticDateResp> query30OrderCount();

    List<StatisticDateResp> query30OrderAmount();
}

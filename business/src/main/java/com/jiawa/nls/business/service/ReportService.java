package com.jiawa.nls.business.service;

import cn.hutool.core.date.DateUtil;
import com.jiawa.nls.business.mapper.cust.ReportMapperCust;
import com.jiawa.nls.business.resp.StatisticDateResp;
import com.jiawa.nls.business.resp.StatisticResp;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Resource
    private ReportMapperCust reportMapperCust;

    /**
     * 首页数字统计
     */
    public StatisticResp queryStatistic() {
        // 大屏数字
        StatisticResp statisticResp = new StatisticResp();
        statisticResp.setOnlineCount(reportMapperCust.queryOnlineCount());
        statisticResp.setRegisterCount(reportMapperCust.queryRegisterCount());
        statisticResp.setFiletransCount(reportMapperCust.queryFiletransCount());
        statisticResp.setFiletransSecond(reportMapperCust.queryFiletransSecond());
        statisticResp.setOrderCount(reportMapperCust.queryOrderCount());
        statisticResp.setOrderAmount(reportMapperCust.queryOrderAmount());

        // 30天趋势图
        List<StatisticDateResp> registerCountList = reportMapperCust.query30RegisterCount();
        statisticResp.setRegisterCountList(fill30(registerCountList));

        List<StatisticDateResp> filetransCountList = reportMapperCust.query30FiletransCount();
        statisticResp.setFiletransCountList(fill30(filetransCountList));

        List<StatisticDateResp> filetransSecondList = reportMapperCust.query30FiletransSecond();
        statisticResp.setFiletransSecondList(fill30(filetransSecondList));

        List<StatisticDateResp> orderCountList = reportMapperCust.query30OrderCount();
        statisticResp.setOrderCountList(fill30(orderCountList));

        List<StatisticDateResp> query30OrderAmount = reportMapperCust.query30OrderAmount();
        statisticResp.setOrderAmountList(fill30(query30OrderAmount));

        return statisticResp;
    }

    /**
     * 补齐30天数据，数据库里可能只有20天有数据，查出来的列表就是只有20个数据，需要补齐成30个数据
     * @param list
     */
    public List<StatisticDateResp> fill30(List<StatisticDateResp> list) {
        List<StatisticDateResp> list30 = new ArrayList<>();
        Date now = new Date();
        String dateFormat = "MM-dd";
        // 将两个列表30天的数据合成一个列表
        for (int i = 29; i >= 0; i--) {
            String date = DateUtil.format(DateUtil.offsetDay(now, -i), dateFormat);
            Optional<StatisticDateResp> registerCountOptional = list.stream().filter(o -> date.equals(o.getDate())).findFirst();
            if (registerCountOptional.isPresent()) {
                list30.add(registerCountOptional.get());
            } else {
                // 如果某天没有数据，则补0
                // log.info("日期【{}】没数据，补0", date);
                StatisticDateResp statisticDateResp = new StatisticDateResp(date, 0);
                list30.add(statisticDateResp);
            }
        }
        return list30;
    }

}

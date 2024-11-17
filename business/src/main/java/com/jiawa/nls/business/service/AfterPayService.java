package com.jiawa.nls.business.service;

import com.jiawa.nls.business.domain.OrderInfo;
import com.jiawa.nls.business.enums.OrderInfoOrderTypeEnum;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@Service
public class AfterPayService {

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private FiletransService filetransService;

    @Transactional
    public void afterPaySuccess(String orderNo, Date channelTime) {
        log.info("执行支付成功动作开始");
        // 校验订单是否存在
        OrderInfo orderInfo = orderInfoService.selectByOrderNo(orderNo);
        if (orderInfo.equals(new OrderInfo())) {
            log.error("订单不存在，{}", orderNo);
            return;
        }

        // 将订单更新成S
        log.info("更新订单信息开始");
        int i = orderInfoService.afterPaySuccess(orderNo, channelTime);
        if (i == 0) {
            log.error("订单状态异常，订单状态非初始，{}，结束", orderNo);
            return;
        }

        if (orderInfo.getOrderType().equals(OrderInfoOrderTypeEnum.FILETRANS_PAY.getCode())) {
            // 将语音识别记录更新成SI
            log.info("语音识别单次付费，更新语音识别表状态");
            Long filetransId = Long.valueOf(orderInfo.getInfo());
            filetransService.afterPaySuccess(filetransId);
        }

        log.info("执行支付成功动作结束");
    }
}

package com.jiawa.nls.business.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alipay.easysdk.payment.common.models.AlipayTradeQueryResponse;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.jiawa.nls.business.alipay.AliPayService;
import com.jiawa.nls.business.context.LoginMemberContext;
import com.jiawa.nls.business.domain.OrderInfo;
import com.jiawa.nls.business.domain.OrderInfoExample;
import com.jiawa.nls.business.enums.OrderInfoChannelEnum;
import com.jiawa.nls.business.enums.OrderInfoStatusEnum;
import com.jiawa.nls.business.exception.BusinessException;
import com.jiawa.nls.business.exception.BusinessExceptionEnum;
import com.jiawa.nls.business.mapper.OrderInfoMapper;
import com.jiawa.nls.business.req.OrderInfoPayReq;
import com.jiawa.nls.business.resp.OrderInfoPayResp;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class OrderInfoService {

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Resource
    private AliPayService aliPayService;

    @Resource
    private AfterPayService afterPayService;

    public OrderInfoPayResp pay(OrderInfoPayReq req) {
        Date now = new Date();

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setId(IdUtil.getSnowflakeNextId());
        String orderNo = genOrderNo();
        orderInfo.setOrderNo(orderNo);
        orderInfo.setOrderAt(now);
        orderInfo.setOrderType(req.getOrderType());
        orderInfo.setInfo(req.getInfo());
        orderInfo.setMemberId(LoginMemberContext.getId());
        orderInfo.setAmount(req.getAmount());
        orderInfo.setPayAt(now);
        orderInfo.setChannel(req.getChannel());
        orderInfo.setChannelAt(null);
        orderInfo.setStatus(OrderInfoStatusEnum.I.getCode());
        orderInfo.setDesc(req.getDesc());
        orderInfo.setCreatedAt(now);
        orderInfo.setUpdatedAt(now);
        orderInfoMapper.insert(orderInfo);

        OrderInfoPayResp orderInfoPayResp = new OrderInfoPayResp();
        orderInfoPayResp.setOrderNo(orderNo);

        // 请求支付宝接口
        if (OrderInfoChannelEnum.ALIPAY.getCode().equals(req.getChannel())) {
            // 调用支付宝下单接口
            AlipayTradePagePayResponse response = aliPayService.pay(req.getDesc(), orderNo, req.getAmount().toPlainString());
            orderInfoPayResp.setChannelResult(response.getBody());
            return orderInfoPayResp;
        } else {
            log.warn("支付渠道【{}】不存在", req.getChannel());
            throw new BusinessException(BusinessExceptionEnum.PAY_ERROR);
        }
    }

    public String genOrderNo() {
        String no = DateUtil.format(new Date(), "yyyyMMddHHmmssSSS");
        int random = (int) (Math.random() * 900 + 100);
        no = no + random;
        return no;
    }

    /**
     * 查询本地订单状态并返回
     * @param orderNo
     * @return
     */
    public String queryOrderStatus(String orderNo) {
        OrderInfo orderInfo = this.selectByOrderNo(orderNo);

        // 全链路查询
        if (OrderInfoStatusEnum.I.getCode().equals(orderInfo.getStatus())) {
            if (OrderInfoChannelEnum.ALIPAY.getCode().equals(orderInfo.getChannel())) {
                AlipayTradeQueryResponse response = aliPayService.query(orderNo);
                String tradeStatus = response.getTradeStatus();
                if ("TRADE_SUCCESS".equals(tradeStatus) || "TRADE_FINISHED".equals(tradeStatus)) {
                    String sendPayDate = response.getSendPayDate();
                    Date date = DateUtil.parse(sendPayDate, "yyyy-MM-dd HH:mm:ss");
                    afterPayService.afterPaySuccess(orderNo, date);
                    return OrderInfoStatusEnum.S.getCode();
                }
            }
        }
        return orderInfo.getStatus();
    }

    public OrderInfo selectByOrderNo(String orderNo) {
        OrderInfoExample orderInfoExample = new OrderInfoExample();
        OrderInfoExample.Criteria criteria = orderInfoExample.createCriteria();
        criteria.andOrderNoEqualTo(orderNo);
        List<OrderInfo> list = orderInfoMapper.selectByExample(orderInfoExample);
        if (CollectionUtils.isEmpty(list)) {
            return new OrderInfo();
        }
        return list.get(0);
    }

    /**
     * 支付成功后，将订单更新成S，按状态更新，从I改成S
     * @param orderNo
     * @param channelTime
     */
    public int afterPaySuccess(String orderNo, Date channelTime) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setStatus(OrderInfoStatusEnum.S.getCode());
        orderInfo.setChannelAt(channelTime);
        orderInfo.setUpdatedAt(new Date());
        OrderInfoExample orderInfoExample = new OrderInfoExample();
        OrderInfoExample.Criteria criteria = orderInfoExample.createCriteria();
        criteria.andOrderNoEqualTo(orderNo).andStatusEqualTo(OrderInfoStatusEnum.I.getCode());
        return orderInfoMapper.updateByExampleSelective(orderInfo, orderInfoExample);
    }
}

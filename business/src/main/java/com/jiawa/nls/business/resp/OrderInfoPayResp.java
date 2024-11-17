package com.jiawa.nls.business.resp;

import lombok.Data;

@Data
public class OrderInfoPayResp {

    private String orderNo;

    /**
     * 调用支付渠道的返回值
     */
    private String channelResult;
}

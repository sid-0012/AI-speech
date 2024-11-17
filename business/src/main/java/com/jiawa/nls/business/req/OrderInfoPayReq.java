package com.jiawa.nls.business.req;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderInfoPayReq {

    private String orderType;

    private String info;

    private BigDecimal amount;

    private String channel;

    private String desc;
}

package com.jiawa.nls.business.req;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class FiletransPayReq {

    private Long memberId;

    private String name;

    private Integer second;

    private BigDecimal amount;

    private String audio;

    private String fileSign;

    private String payStatus;

    private String status;

    private String lang;

    private String vod;

    private String taskId;

    private Integer transStatusCode;

    private String transStatusText;

    private Date transTime;

    private Date solveTime;

    private String channel;

}

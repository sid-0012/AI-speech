package com.jiawa.nls.business.req;

import lombok.Data;

@Data
public class FiletransQueryReq extends PageReq {

    private Long memberId;

    private String lang;

    private String status;

    private String name;
}

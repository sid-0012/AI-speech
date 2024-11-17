package com.jiawa.nls.business.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class MemberLoginResp {

    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    private String name;

    private String token;
}

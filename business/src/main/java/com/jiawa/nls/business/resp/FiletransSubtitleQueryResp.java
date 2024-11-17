package com.jiawa.nls.business.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

@Data
public class FiletransSubtitleQueryResp {

    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    @JsonSerialize(using= ToStringSerializer.class)
    private Long filetransId;

    private Integer index;

    private Integer begin;

    private Integer end;

    private String text;

    private Date createdAt;

    private Date updatedAt;

}

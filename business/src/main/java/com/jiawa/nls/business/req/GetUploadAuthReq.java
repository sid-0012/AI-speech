package com.jiawa.nls.business.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GetUploadAuthReq {

    /**
     * 文件名
     */
    @NotBlank(message = "【文件名】不能为空")
    private String name;

    /**
     * 文件标识，前端对文件做md5得到的值
     */
    @NotBlank(message = "【文件标识】不能为空")
    private String key;

}

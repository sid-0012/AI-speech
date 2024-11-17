package com.jiawa.nls.business.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SmsCodeRegisterReq {

    @NotBlank(message = "【手机号】不能为空")
    private String mobile;

    /**
     * 验证码
     */
    @NotBlank(message = "【图片验证码】不能为空")
    private String imageCode;

    /**
     * 图片验证码token
     */
    @NotBlank(message = "【图片验证码】参数非法")
    private String imageCodeToken;
}

package com.jiawa.nls.business.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginReq {

    /**
     * 登录名
     */
    @NotBlank(message = "【登录名】不能为空")
    private String loginName;

    /**
     * 密码
     */
    @NotBlank(message = "【密码】不能为空")
    private String password;

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

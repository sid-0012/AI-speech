package com.jiawa.nls.business.controller.web;

import com.jiawa.nls.business.req.SmsCodeRegisterReq;
import com.jiawa.nls.business.req.SmsCodeResetReq;
import com.jiawa.nls.business.resp.CommonResp;
import com.jiawa.nls.business.service.KaptchaService;
import com.jiawa.nls.business.service.SmsCodeService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web/sms-code")
public class WebSmsCodeController {

    @Resource
    private SmsCodeService smsCodeService;

    @Resource
    private KaptchaService kaptchaService;

    @PostMapping("/send-for-register")
    public CommonResp<Object> sendForRegister(@Valid @RequestBody SmsCodeRegisterReq req) {

        // 校验图片验证码，防止短信攻击，不加的话，只能防止同一手机攻击，加上图片验证码，可防止不同的手机号攻击
        kaptchaService.validCode(req.getImageCode(), req.getImageCodeToken());

        smsCodeService.sendCodeForRegister(req.getMobile());
        return new CommonResp<>();
    }

    @PostMapping("/send-for-reset")
    public CommonResp<Object> sendForReset(@Valid @RequestBody SmsCodeResetReq req) {

        // 校验图片验证码，防止短信攻击，不加的话，只能防止同一手机攻击，加上图片验证码，可防止不同的手机号攻击
        kaptchaService.validCode(req.getImageCode(), req.getImageCodeToken());

        smsCodeService.sendCodeForReset(req.getMobile());
        return new CommonResp<>();
    }
}

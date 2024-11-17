package com.jiawa.nls.business.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.jiawa.nls.business.domain.Member;
import com.jiawa.nls.business.domain.SmsCode;
import com.jiawa.nls.business.domain.SmsCodeExample;
import com.jiawa.nls.business.enums.SmsCodeStatusEnum;
import com.jiawa.nls.business.enums.SmsCodeUseEnum;
import com.jiawa.nls.business.exception.BusinessException;
import com.jiawa.nls.business.exception.BusinessExceptionEnum;
import com.jiawa.nls.business.mapper.SmsCodeMapper;
import com.jiawa.nls.business.util.SmsUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class SmsCodeService {

    @Resource
    private SmsCodeMapper smsCodeMapper;

    @Resource
    private MemberService memberService;

    public void sendCodeForRegister(String mobile) {
        Member member = memberService.selectByMobile(mobile);
        if (member != null) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_HAD_REGISTER);
        }
        sendCode(mobile, SmsCodeUseEnum.REGISTER.getCode());
    }

    public void sendCodeForReset(String mobile) {
        Member member = memberService.selectByMobile(mobile);
        if (member == null) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_NOT_REGISTER);
        }
        sendCode(mobile, SmsCodeUseEnum.RESET.getCode());
    }

    /**
     * 发送短信验证码
     * 校验：如果1分钟内有同手机号同用途发送记录，则报错：短信请求过于频繁
     * @param mobile 手机号
     * @param use 用途
     */
    private void sendCode(String mobile, String use) {
        Date now = new Date();
        String code = RandomUtil.randomNumbers(6);

        log.info("当前时间：", new Date());
        log.info("1分钟以前：", DateUtil.offsetMinute(new Date(), -1));

        SmsCodeExample smsCodeExample = new SmsCodeExample();
        SmsCodeExample.Criteria criteria = smsCodeExample.createCriteria();
        criteria.andMobileEqualTo(mobile).andUseEqualTo(use).andCreatedAtGreaterThan(DateUtil.offsetMinute(new Date(), -1));
        long count = smsCodeMapper.countByExample(smsCodeExample);
        if (count > 0) {
            throw new BusinessException(BusinessExceptionEnum.SMS_CODE_TOO_FREQUENT);
        }

        // 保存数据库
        SmsCode smsCode = new SmsCode();
        smsCode.setId(IdUtil.getSnowflakeNextId());
        smsCode.setMobile(mobile);
        smsCode.setCode(code);
        smsCode.setUse(use);
        smsCode.setStatus(SmsCodeStatusEnum.NOT_USED.getCode());
        smsCode.setCreatedAt(now);
        smsCode.setUpdatedAt(now);
        smsCodeMapper.insert(smsCode);

        // 对接短信通道，发送短信
        SmsUtil.sendCode(mobile, code);
    }

    /**
     * 验证码校验:
     * 5分钟内、同手机号、同用途、未使用过的验证码才算有效
     * 只校验最后一次验证码
     */
    public void validCode(String mobile, String use, String code) {
        SmsCodeExample smsCodeExample = new SmsCodeExample();
        SmsCodeExample.Criteria criteria = smsCodeExample.createCriteria();
        criteria.andMobileEqualTo(mobile)
                .andUseEqualTo(use)
                .andCreatedAtGreaterThan(DateUtil.offsetMinute(new Date(), -5))
                .andStatusEqualTo(SmsCodeStatusEnum.NOT_USED.getCode());
        smsCodeExample.setOrderByClause("created_at desc");
        List<SmsCode> list = smsCodeMapper.selectByExample(smsCodeExample);
        if (CollUtil.isNotEmpty(list)) {
            SmsCode smsCode = list.get(0);
            if (smsCode.getCode().equals(code)) {
                smsCode.setStatus(SmsCodeStatusEnum.USED.getCode());
                smsCode.setUpdatedAt(new Date());
                smsCodeMapper.updateByPrimaryKey(smsCode);
            } else {
                log.warn("验证码不正确，手机号：{}，输入验证码：{}，用途：{}", mobile, code, use);
                throw new BusinessException(BusinessExceptionEnum.SMS_CODE_ERROR);
            }
        } else {
            log.warn("验证码未发送或已过期，手机号：{}，输入验证码：{}，用途：{}", mobile, code, use);
            throw new BusinessException(BusinessExceptionEnum.SMS_CODE_EXPIRED);
        }
    }
}

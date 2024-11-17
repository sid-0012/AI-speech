package com.jiawa.nls.business.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import com.jiawa.nls.business.domain.Member;
import com.jiawa.nls.business.domain.MemberExample;
import com.jiawa.nls.business.exception.BusinessException;
import com.jiawa.nls.business.exception.BusinessExceptionEnum;
import com.jiawa.nls.business.mapper.MemberMapper;
import com.jiawa.nls.business.req.MemberLoginReq;
import com.jiawa.nls.business.req.MemberRegisterReq;
import com.jiawa.nls.business.req.MemberResetReq;
import com.jiawa.nls.business.resp.MemberLoginResp;
import com.jiawa.nls.business.util.JwtUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class MemberService {

    @Resource
    private MemberMapper memberMapper;

    @Resource
    private MemberLoginLogService memberLoginLogService;

    /**
     * 按手机号查会员信息
     * @param mobile
     * @return
     */
    public Member selectByMobile(String mobile) {
        MemberExample memberExample = new MemberExample();
        MemberExample.Criteria criteria = memberExample.createCriteria();
        criteria.andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);
        if (CollUtil.isNotEmpty(list)) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 注册
     */
    public void register(MemberRegisterReq req) {
        Date now = new Date();
        String mobile = req.getMobile();
        Member memberDB = selectByMobile(mobile);
        if (memberDB != null) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_HAD_REGISTER);
        }
        Member member = new Member();
        member.setId(IdUtil.getSnowflakeNextId());
        member.setMobile(mobile);
        member.setPassword(req.getPassword());
        member.setName(mobile.substring(0, 3) + "****" + mobile.substring(7));
        member.setCreatedAt(now);
        member.setUpdatedAt(now);
        memberMapper.insert(member);
    }

    /**
     * 登录
     */
    public MemberLoginResp login(MemberLoginReq req) {
        Member memberDB = selectByMobile(req.getMobile());
        if (memberDB == null) {
            log.warn("手机号不存在，{}", req.getMobile());
            throw new BusinessException(BusinessExceptionEnum.MEMBER_LOGIN_ERROR);
        }

        if (memberDB.getPassword().equalsIgnoreCase(req.getPassword())) {
            log.info("登录成功，{}", req.getMobile());
            MemberLoginResp memberLoginResp = new MemberLoginResp();
            memberLoginResp.setId(memberDB.getId());
            memberLoginResp.setName(memberDB.getName());

            Map<String, Object> map = BeanUtil.beanToMap(memberLoginResp);
            String token = JwtUtil.createLoginToken(map);
            memberLoginResp.setToken(token);

            memberLoginLogService.save(memberLoginResp);

            return memberLoginResp;
        } else {
            log.warn("密码错误，{}", req.getMobile());
            throw new BusinessException(BusinessExceptionEnum.MEMBER_LOGIN_ERROR);
        }

    }

    /**
     * 重置密码
     */
    public void reset(MemberResetReq req) {
        Date now = new Date();
        String mobile = req.getMobile();
        Member memberDB = selectByMobile(mobile);
        if (memberDB == null) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_NOT_REGISTER);
        }
        Member member = new Member();
        member.setId(memberDB.getId());
        member.setPassword(req.getPassword());
        member.setUpdatedAt(now);
        memberMapper.updateByPrimaryKeySelective(member);
    }

}

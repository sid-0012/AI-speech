package com.jiawa.nls.business.context;

import com.jiawa.nls.business.resp.MemberLoginResp;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginMemberContext {

    private static ThreadLocal<MemberLoginResp> member = new ThreadLocal<>();

    public static MemberLoginResp getMember() {
        return member.get();
    }

    public static void setMember(MemberLoginResp member) {
        LoginMemberContext.member.set(member);
    }

    public static void removeMember() {
        LoginMemberContext.member.remove();
    }

    public static Long getId() {
        try {
            return member.get().getId();
        } catch (Exception e) {
            log.error("获取登录会员信息异常", e);
            throw e;
        }
    }

}

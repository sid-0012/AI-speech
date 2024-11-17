package com.jiawa.nls.business.context;

import com.jiawa.nls.business.resp.UserLoginResp;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginUserContext {

    private static ThreadLocal<UserLoginResp> user = new ThreadLocal<>();

    public static UserLoginResp getUser() {
        return user.get();
    }

    public static void setUser(UserLoginResp user) {
        LoginUserContext.user.set(user);
    }

    public static void removeUser() {
        LoginUserContext.user.remove();
    }

    public static Long getId() {
        try {
            return user.get().getId();
        } catch (Exception e) {
            log.error("获取登录用户信息异常", e);
            throw e;
        }
    }

}

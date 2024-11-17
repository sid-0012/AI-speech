package com.jiawa.nls.business.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.jiawa.nls.business.domain.User;
import com.jiawa.nls.business.domain.UserExample;
import com.jiawa.nls.business.exception.BusinessException;
import com.jiawa.nls.business.exception.BusinessExceptionEnum;
import com.jiawa.nls.business.mapper.UserMapper;
import com.jiawa.nls.business.req.UserLoginReq;
import com.jiawa.nls.business.resp.UserLoginResp;
import com.jiawa.nls.business.util.JwtUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public User selectByLoginName(String loginName) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andLoginNameEqualTo(loginName);
        List<User> list = userMapper.selectByExample(userExample);
        if (CollUtil.isNotEmpty(list)) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 登录
     */
    public UserLoginResp login(UserLoginReq req) {
        User userDB = selectByLoginName(req.getLoginName());
        if (userDB == null) {
            log.warn("登录名不存在，{}", req.getLoginName());
            throw new BusinessException(BusinessExceptionEnum.USER_LOGIN_ERROR);
        }

        if (userDB.getPassword().equalsIgnoreCase(req.getPassword())) {
            log.info("登录成功，{}", req.getLoginName());
            UserLoginResp userLoginResp = new UserLoginResp();
            userLoginResp.setId(userDB.getId());
            userLoginResp.setLoginName(userDB.getLoginName());

            Map<String, Object> map = BeanUtil.beanToMap(userLoginResp);
            String token = JwtUtil.createLoginToken(map);
            userLoginResp.setToken(token);
            return userLoginResp;
        } else {
            log.warn("密码错误，{}", req.getLoginName());
            throw new BusinessException(BusinessExceptionEnum.USER_LOGIN_ERROR);
        }

    }

}

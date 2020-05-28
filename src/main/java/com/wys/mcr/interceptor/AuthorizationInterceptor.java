package com.wys.mcr.interceptor;

import com.wys.mcr.common.ContextHolder;
import com.wys.mcr.common.annotation.Login;
import com.wys.mcr.common.enums.ResponseCodeEnum;
import com.wys.mcr.common.exception.MyException;
import com.wys.mcr.entity.Token;
import com.wys.mcr.entity.SysUser;
import com.wys.mcr.service.TokenService;
import com.wys.mcr.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * 权限(Token)验证
 *
 * @author lcw
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Value("${api.minutes}")
    private Integer minutes;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Login annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(Login.class);
        } else {
            return true;
        }

        if (annotation == null) {
            return true;
        }

        //从header中获取token
        String token = request.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = request.getParameter("token");
        }

        //token为空
        if (StringUtils.isBlank(token)) {
            throw new MyException("token不能为空");
        }

        //查询token信息
        Token tokenEntity = tokenService.queryByToken(token);
        if (tokenEntity == null || tokenEntity.getExpireTime().isBefore(LocalDateTime.now())) {
            throw new MyException(ResponseCodeEnum.TOKEN_INVALID.getMessage(), ResponseCodeEnum.TOKEN_INVALID.getCode());
        }
        // 用户操作后刷新登录超时时间createUserId
        tokenEntity.setUpdateTime(LocalDateTime.now());
        tokenEntity.setExpireTime(LocalDateTime.now().plusMinutes(minutes));
        tokenService.updateById(tokenEntity);
        //获取用户信息，将部门id存入
        SysUser user = userService.getById(tokenEntity.getUserId());
        ContextHolder.setUserId(user.getUserId());
        ContextHolder.setUserName(user.getUsername());
        return true;
    }
}

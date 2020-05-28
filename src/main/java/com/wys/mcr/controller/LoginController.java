

package com.wys.mcr.controller;

import com.wys.mcr.common.annotation.Login;
import com.wys.mcr.common.annotation.SysLog;
import com.wys.mcr.common.utils.R;
import com.wys.mcr.form.LoginForm;
import com.wys.mcr.service.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 登录相关
 *
 * @author Mark sunlightcs@gmail.com
 */
@Api(tags = "登录接口")
@RestController
@RequestMapping("/sys")
public class LoginController {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 登录
     */
    @SysLog("后台登录")
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginForm form) {
        Map<String, Object> map = sysUserService.login(form);
        return R.ok(map);
    }


    /**
     * 退出
     */
    @Login
    @PostMapping("/logout")
    public R logout() {
        sysUserService.logout();
        return R.ok();
    }

}

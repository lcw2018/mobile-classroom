package com.wys.mcr.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wys.mcr.common.annotation.Login;
import com.wys.mcr.common.annotation.SysLog;
import com.wys.mcr.common.dto.req.UserAddReq;
import com.wys.mcr.common.dto.req.UserReq;
import com.wys.mcr.common.dto.resp.UserResp;
import com.wys.mcr.common.utils.Base64Utils;
import com.wys.mcr.common.utils.R;
import com.wys.mcr.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 登录接口
 *
 * @author lcw
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Login
    @SysLog("用户冻结")
    @ApiOperation("用户冻结")
    @PostMapping("freezen")
    public R freezen(@RequestBody UserReq userReq) {
        userService.freezen(userReq.getUserId());
        return R.ok();
    }

    @ApiOperation("app用户列表查询")
    @Login
    @GetMapping("/page")
    public R page(UserReq userReq) {
        IPage<UserResp> userRespIPage = userService.page(userReq);
        return R.ok().put("data", userRespIPage);
    }

    @Login
    @SysLog("密码重置")
    @ApiOperation("密码重置")
    @PostMapping("reset")
    public R reset(@RequestBody UserReq userReq) {
        userService.reset(userReq.getUserId());
        return R.ok();
    }

    @Login
    @SysLog("添加用户")
    @ApiOperation("添加用户")
    @PostMapping("add")
    public R add(@RequestBody UserAddReq userAddReq) {
        userService.add(userAddReq);
        return R.ok();
    }

    @Login
    @SysLog("修改用户角色")
    @ApiOperation("修改用户角色")
    @PostMapping("editRole")
    public R editRole(@RequestBody UserReq userReq) {
        userService.editRole(userReq);
        return R.ok();
    }
}

//
//
//package com.wys.mcr.controller;
//
//import com.wys.mcr.common.annotation.Login;
//import com.wys.mcr.common.annotation.SysLog;
//import com.wys.mcr.common.dto.req.SysUserReq;
//import com.wys.mcr.common.utils.PageUtils;
//import com.wys.mcr.common.utils.R;
//import com.wys.mcr.common.validator.ValidatorUtils;
//import com.wys.mcr.common.validator.group.AddGroup;
//import com.wys.mcr.common.validator.group.UpdateGroup;
//import com.wys.mcr.entity.SysUser;
//import com.wys.mcr.form.PasswordForm;
//import com.wys.mcr.service.SysUserService;
//import io.swagger.annotations.Api;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import springfox.documentation.annotations.ApiIgnore;
//
///**
// * 系统用户
// *
// * @author Mark sunlightcs@gmail.com
// */
//@ApiIgnore
//@Api(tags = "系统用户接口")
//@RestController
//@RequestMapping("/sysUser")
//public class SysUserController {
//    @Autowired
//    private SysUserService sysUserService;
//
//    /**
//     * 所有用户列表
//     */
//    @GetMapping("/list")
//    @Login
//    public R list(SysUserReq userReq) {
//        PageUtils page = sysUserService.queryPage(userReq);
//        return R.ok().put("data", page);
//    }
//
//    /**
//     * 获取登录的用户信息
//     */
//    @GetMapping("/info")
//    @Login
//    public R info() {
//        SysUser sysUser = sysUserService.getCurrentUser();
//        return R.ok().put("data", sysUser);
//    }
//
//    /**
//     * 修改登录用户密码
//     */
//    @SysLog("修改密码")
//    @Login
//    @PostMapping("/editPassword")
//    public R editPassword(@RequestBody PasswordForm form) {
//        sysUserService.editPassword(form);
//        return R.ok();
//    }
//
//    /**
//     * 用户信息
//     */
//    @GetMapping("/info/{userId}")
//    public R info(@PathVariable("userId") String userId) {
//        SysUser user = sysUserService.getById(userId);
//
//        return R.ok().put("data", user);
//    }
//
//    /**
//     * 保存用户
//     */
//    @SysLog("保存用户")
//    @Login
//    @PostMapping("/save")
//    public R save(@RequestBody SysUser user) {
//        ValidatorUtils.validateEntity(user, AddGroup.class);
//
//        user.setCreateId(sysUserService.getCurrentUser().getUserId());
//        sysUserService.saveUser(user);
//
//        return R.ok();
//    }
//
//    /**
//     * 修改用户
//     */
//    @SysLog("修改用户")
//    @Login
//    @PostMapping("/update")
//    public R update(@RequestBody SysUser user) {
//        ValidatorUtils.validateEntity(user, UpdateGroup.class);
//
//        user.setCreateId(sysUserService.getCurrentUser().getUserId());
//        sysUserService.update(user);
//
//        return R.ok();
//    }
//}

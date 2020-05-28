package com.wys.mcr.controller;

import com.wys.mcr.common.annotation.Login;
import com.wys.mcr.common.utils.R;
import com.wys.mcr.entity.Role;
import com.wys.mcr.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author lcw
 * @since 2019-06-08
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @ApiOperation("角色列表查询")
    @Login
    @GetMapping("list")
    public R list() {
        List<Role> roleList = roleService.list();
        return R.ok().put("data", roleList);
    }
}


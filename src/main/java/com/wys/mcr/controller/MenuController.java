package com.wys.mcr.controller;


import com.wys.mcr.common.annotation.Login;
import com.wys.mcr.common.annotation.SysLog;
import com.wys.mcr.common.dto.req.MenuReq;
import com.wys.mcr.common.utils.R;
import com.wys.mcr.entity.Menu;
import com.wys.mcr.entity.MenuType;
import com.wys.mcr.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单管理 前端控制器
 * </p>
 *
 * @author lcw
 * @since 2019-05-30
 */
@Api(tags = "菜单接口")
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @ApiOperation("app菜单列表查询")
    @Login
    @GetMapping("/list")
    public R list(MenuReq menuReq) {
        List<Menu> menuList = menuService.list(menuReq);
        return R.ok().put("data", menuList);
    }

    @ApiOperation("菜单排序列表查询")
    @Login
    @GetMapping("orderList")
    public R getDeptService() {
        List<Menu> menuList = menuService.orderList();
        return R.ok().put("data", menuList);
    }

    @SysLog("保存菜单")
    @Login
    @PostMapping("/save")
    public R save(@RequestBody MenuReq menuReq) {
        menuService.save(menuReq);

        return R.ok();
    }

    @SysLog("修改菜单")
    @Login
    @PostMapping("/update")
    public R update(@RequestBody MenuReq menuReq) {
        menuService.update(menuReq);
        return R.ok();
    }

    @Login
    @GetMapping("/getType")
    public R getType() {
        List<MenuType> menuTypeList = menuService.getType();
        return R.ok().put("data", menuTypeList);
    }
}


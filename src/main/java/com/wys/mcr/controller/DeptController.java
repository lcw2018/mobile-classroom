package com.wys.mcr.controller;


import com.wys.mcr.common.annotation.Login;
import com.wys.mcr.common.annotation.SysLog;
import com.wys.mcr.common.dto.req.DeptReq;
import com.wys.mcr.common.utils.R;
import com.wys.mcr.common.validator.ValidatorUtils;
import com.wys.mcr.entity.Dept;
import com.wys.mcr.service.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理
 *
 * @author lcw
 * @since 2019-05-28
 */
@Api(tags = "部门接口")
@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @ApiOperation("部门条件列表查询")
    @Login
    @GetMapping("list")
    public R list(DeptReq deptReq) {
        List<Dept> deptList = deptService.list(deptReq);
        return R.ok().put("data", deptList);
    }

    @ApiOperation("部门排序列表查询")
    @Login
    @GetMapping("orderList")
    public R orderList() {
        List<Dept> deptList = deptService.orderList();
        return R.ok().put("data", deptList);
    }

    @ApiOperation("部门名称拼接列表查询")
    @Login
    @GetMapping("nameList")
    public R nameList() {
        List<Dept> nameList = deptService.nameList();
        return R.ok().put("data", nameList);
    }


    @ApiOperation("部门保存")
    @SysLog("部门保存")
    @Login
    @PostMapping("/save")
    public R save(@RequestBody DeptReq deptReq) {
        //数据校验
        ValidatorUtils.validateEntity(deptReq, DeptReq.Save.class);
        deptService.save(deptReq);

        return R.ok();
    }

    @ApiOperation("部门修改")
    @SysLog("部门修改")
    @Login
    @PostMapping("/update")
    public R update(@RequestBody DeptReq deptReq) {
        deptService.update(deptReq);

        return R.ok();
    }

    @ApiOperation("部门删除")
    @SysLog("部门删除")
    @Login
    @PostMapping("/delete")
    public R delete(@RequestBody DeptReq deptReq) {
        deptService.delete(deptReq);

        return R.ok();
    }

}


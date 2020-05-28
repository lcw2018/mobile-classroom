package com.wys.mcr.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wys.mcr.common.annotation.Login;
import com.wys.mcr.common.dto.req.CheckReq;
import com.wys.mcr.common.dto.resp.CheckResp;
import com.wys.mcr.common.utils.R;
import com.wys.mcr.service.ExamCheckService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lcw
 * @since 2019-06-07
 */
@Api(tags = "考核-考相关")
@RestController
@RequestMapping("/examCheck")
public class ExamCheckController {
    @Autowired
    private ExamCheckService examCheckService;

    @ApiOperation("一级考的考核分页列表")
    @Login
    @GetMapping("/firstPage")
    public R firstPage(CheckReq checkReq) {
        IPage<CheckResp> checkRespIPage = examCheckService.firstPage(checkReq);
        return R.ok().put("data", checkRespIPage);
    }

    @ApiOperation("二级考的考核分页列表")
    @Login
    @GetMapping("/secondPage")
    public R secondPage(CheckReq checkReq) {
        IPage<CheckResp> checkRespIPage = examCheckService.secondPage(checkReq);
        return R.ok().put("data", checkRespIPage);
    }

    @ApiOperation("三级考的考核分页列表")
    @Login
    @GetMapping("/thirdPage")
    public R thirdPage(CheckReq checkReq) {
        IPage<CheckResp> checkRespIPage = examCheckService.thirdPage(checkReq);
        return R.ok().put("data", checkRespIPage);
    }
}


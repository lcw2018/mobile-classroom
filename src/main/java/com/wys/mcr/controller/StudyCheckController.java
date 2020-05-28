package com.wys.mcr.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wys.mcr.common.annotation.Login;
import com.wys.mcr.common.dto.req.CheckReq;
import com.wys.mcr.common.dto.resp.CheckResp;
import com.wys.mcr.common.utils.R;
import com.wys.mcr.service.StudyCheckService;
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
@Api(tags = "考核-学相关")
@RestController
@RequestMapping("/studyCheck")
public class StudyCheckController {

    @Autowired
    private StudyCheckService studyCheckService;

    @ApiOperation("一级练的考核分页列表")
    @Login
    @GetMapping("/firstPage")
    public R firstPage(CheckReq checkReq) {
        IPage<CheckResp> checkRespIPage = studyCheckService.firstPage(checkReq);
        return R.ok().put("data", checkRespIPage);
    }

    @ApiOperation("二级练的考核分页列表")
    @Login
    @GetMapping("/secondPage")
    public R secondPage(CheckReq checkReq) {
        IPage<CheckResp> checkRespIPage = studyCheckService.secondPage(checkReq);
        return R.ok().put("data", checkRespIPage);
    }
}


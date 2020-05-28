package com.wys.mcr.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wys.mcr.common.annotation.Login;
import com.wys.mcr.common.annotation.SysLog;
import com.wys.mcr.common.dto.req.StudyResourceReq;
import com.wys.mcr.common.dto.resp.StudyResourceResp;
import com.wys.mcr.common.utils.R;
import com.wys.mcr.entity.StudyResource;
import com.wys.mcr.service.StudyResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lcw
 * @since 2019-06-01
 */
@Api(tags = "学习资料")
@RestController
@RequestMapping("/studyResource")
public class StudyResourceController {
    @Autowired
    private StudyResourceService studyResourceService;

    @ApiOperation("学习资料保存")
    @SysLog(value = "学习资料保存", isSaveParam = "no")
    @Login
    @PostMapping("saveStudyResource")
    public R saveStudyResource(@RequestBody StudyResourceReq studyResourceReq) {
        studyResourceService.saveStudyResource(studyResourceReq);
        return R.ok();
    }

    @ApiOperation("学习资料分页列表")
    @Login
    @GetMapping("page")
    public R page(StudyResourceReq studyResourceReq) {
        IPage<StudyResourceResp> studyResourceRespIPage = studyResourceService.page(studyResourceReq);
        return R.ok().put("data", studyResourceRespIPage);
    }

    @ApiOperation("学习资料修改")
    @SysLog(value = "学习资料修改", isSaveParam = "no")
    @Login
    @PostMapping("edit")
    public R edit(@RequestBody StudyResourceReq studyResourceReq) {
        studyResourceService.edit(studyResourceReq);
        return R.ok();
    }

    @ApiOperation("学习资料删除")
    @SysLog("学习资料删除")
    @Login
    @PostMapping("delete")
    public R delete(@RequestBody StudyResourceReq studyResourceReq) {
        studyResourceService.delete(studyResourceReq);
        return R.ok();
    }

    @ApiOperation("获取单个学习资料")
    @Login
    @GetMapping("get")
    public R get(String id) {
        StudyResource studyResource = studyResourceService.getById(id);
        return R.ok().put("data", studyResource);
    }

}


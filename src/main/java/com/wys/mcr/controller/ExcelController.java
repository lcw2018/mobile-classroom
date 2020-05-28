package com.wys.mcr.controller;

import com.wys.mcr.common.annotation.Login;
import com.wys.mcr.common.annotation.SysLog;
import com.wys.mcr.common.dto.req.CheckReq;
import com.wys.mcr.common.dto.req.ExcelReq;
import com.wys.mcr.common.utils.R;
import com.wys.mcr.service.ExcelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: lcw
 * @Date: 2019/6/7
 */
@Api(tags = "excel文件上传")
@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @ApiOperation("excel模板导出")
    @Login
    @PostMapping("/exportTemplate")
    public void exportTemplate(@RequestBody ExcelReq excelReq) {
        excelService.exportTemplate(excelReq);
    }

    @ApiOperation("试题上传")
    @SysLog(value = "excel试题上传", isSaveParam = "no")
    @Login
    @PostMapping("/importResource")
    public R importResource(@RequestParam("file") MultipartFile file, @RequestParam("type") String type, @RequestParam("menuId") String menuId) {
        excelService.importResource(file, type, menuId);
        return R.ok();
    }

    @ApiOperation("excel数据导出")
    @Login
    @PostMapping("/exportData")
    public void exportData(@RequestBody CheckReq checkReq) {
        excelService.exportData(checkReq);
    }
}

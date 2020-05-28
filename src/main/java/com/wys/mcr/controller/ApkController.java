package com.wys.mcr.controller;


import com.wys.mcr.common.annotation.Login;
import com.wys.mcr.common.annotation.SysLog;
import com.wys.mcr.common.utils.R;
import com.wys.mcr.entity.Apk;
import com.wys.mcr.service.ApkService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lcw
 * @since 2019-06-13
 */
@RestController
@RequestMapping("/apk")
public class ApkController {
    @Autowired
    private ApkService apkService;

    @ApiOperation("获取最新的apk码")
    @GetMapping("getNewApk")
    public R getNewApk() {
        Apk apk = apkService.getNewApk();
        return R.ok().put("data", apk);
    }

    @PostMapping("/upload")
    @SysLog("apk上传")
    @Login
    @ApiOperation("apk上传")
    @ResponseBody
    public R uploadApk(@RequestParam("file") MultipartFile file,
                       @RequestParam("versionCode") String versionCode,
                       @RequestParam("versionName") String versionName,
                       @RequestParam("updateLog") String updateLog,
                       @RequestParam("constrain") Integer constrain) {
        apkService.uploadApk(file, versionCode, versionName, updateLog, constrain);
        return R.ok();
    }
}


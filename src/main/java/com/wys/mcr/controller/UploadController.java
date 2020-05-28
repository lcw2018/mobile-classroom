package com.wys.mcr.controller;

import com.wys.mcr.common.annotation.Login;
import com.wys.mcr.common.annotation.SysLog;
import com.wys.mcr.common.utils.R;
import com.wys.mcr.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description
 * @Author sgl
 * @Date 2018-05-15 14:04
 */
@Api(tags = "文件上传")
@Controller
@Slf4j
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    @SysLog(value = "文件上传", isSaveParam = "no")
    @Login
    @ApiOperation("文件上传")
    @ResponseBody
    public R upload(@RequestParam("file") MultipartFile file, @RequestParam(value = "title", required = false) String title, @RequestParam("remark") String remark, @RequestParam String type) {
        uploadService.upload(file, title, remark, type);
        return R.ok();
    }

//    /**
//     * 多文件具体上传时间，主要是使用了MultipartHttpServletRequest和MultipartFile
//     *
//     * @param request
//     * @return
//     */
//    @ApiIgnore
//    @ApiOperation("多文件上传1")
//    @PostMapping(value = "/batch/upload")
//    @ResponseBody
//    public R handleFileUpload(HttpServletRequest request) {
//        String msg = uploadService.batchUpload(request);
//        return R.ok(msg);
//    }
//
//    private String filePath = "/data/m/";
//
//    @ApiIgnore
//    @ApiOperation("多文件上传2")
//    @ResponseBody
//    @PostMapping("/upLoadFile")
//    public String uploadFiles(@RequestParam("file") MultipartFile[] files, @RequestParam("title") String title) {
//        for (int i = 0; i < files.length; i++) {
//            try {
//                uploadFile(files[i].getBytes(), files[i].getOriginalFilename());
//                System.out.println("成功" + i + "-" + title);
//            } catch (Exception e) {
//                e.printStackTrace();
//                log.info("上传文件发生错误");
//            }
//        }
//        return "success";
//    }
//
//    @ApiIgnore
//    private void uploadFile(byte[] file, String fileName) throws Exception {
//        File targetFile = new File(filePath);
//        if (!targetFile.exists()) {
//            targetFile.mkdirs();
//        }
//        FileOutputStream out = new FileOutputStream(filePath + fileName);
//        out.write(file);
//        out.flush();
//        out.close();
//    }
}
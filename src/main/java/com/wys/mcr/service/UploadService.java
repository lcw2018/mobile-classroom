package com.wys.mcr.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: lcw
 * @Date: 2019/6/6
 */
public interface UploadService {

//    String batchUpload(HttpServletRequest request);

    void upload(MultipartFile file, String title, String remark, String type);
}

package com.wys.mcr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wys.mcr.entity.Apk;
import com.wys.mcr.entity.StudyResource;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lcw
 * @since 2019-06-13
 */
public interface ApkService extends IService<Apk> {

    Apk getNewApk();

    void uploadApk(MultipartFile file, String versionCode, String versionName, String updateLog, Integer constrain);
}

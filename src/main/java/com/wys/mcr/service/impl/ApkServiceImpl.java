package com.wys.mcr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wys.mcr.common.exception.MyException;
import com.wys.mcr.common.validator.Assert;
import com.wys.mcr.entity.Apk;
import com.wys.mcr.mapper.ApkMapper;
import com.wys.mcr.service.ApkService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lcw
 * @since 2019-06-13
 */
@Service
@Slf4j
public class ApkServiceImpl extends ServiceImpl<ApkMapper, Apk> implements ApkService {
    @Value("${api.apkPath}")
    private String apkPath;

    @Value("${api.domainAddress}")
    private String domainAddress;

    @Override
    public Apk getNewApk() {
        LambdaQueryWrapper<Apk> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Apk::getCreateDate);
        List<Apk> apkList = list(queryWrapper);
        if (CollectionUtils.isEmpty(apkList)) {
            log.info("未查到安装包信息，请去数据库核实数据是否存在");
            throw new MyException("未查到安装包信息，请去数据库核实数据是否存在");
        }
        return apkList.get(0);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void uploadApk(MultipartFile file, String versionCode, String versionName, String updateLog, Integer constrain) {
        String fileName = file.getOriginalFilename();
        Assert.isBlank(fileName, "文件名不能为空");
        String[] temp = fileName.split("\\.");
        fileName = temp[0] + "_" + versionName + "." + temp[1];
        File dest = new File(apkPath + fileName);
        long targetSize = file.getSize();
        try {
            String apkUrl = domainAddress + "/d/" + fileName;
            LambdaQueryWrapper<Apk> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Apk::getApkFileUrl, apkUrl);
            if (count(queryWrapper) > 0) {
                throw new MyException("地址名重复，请修改文件名称");
            }
            file.transferTo(dest);
            //复制上传的最新apk文件用于下载
            File destDownLoad = new File(apkPath + file.getOriginalFilename());
            FileUtils.copyFile(dest, destDownLoad);
            Apk apk = new Apk();
            apk.setApkFileUrl(apkUrl);
            apk.setTargetSize(String.valueOf((targetSize / 1000000)) + "M");
            apk.setVersionCode(versionCode);
            apk.setVersionName(versionName);
            apk.setUpdateLog(updateLog);
            apk.setConstrain(constrain);
            apk.setCreateDate(LocalDateTime.now());
            save(apk);
        } catch (IOException e) {
            log.error(e.toString(), e);
            throw new MyException("上传失败");
        }
        log.info("上传成功{},{},{},{}", versionCode, versionName, updateLog, constrain);
    }
}

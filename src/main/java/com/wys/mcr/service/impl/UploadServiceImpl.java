package com.wys.mcr.service.impl;

import com.wys.mcr.common.ContextHolder;
import com.wys.mcr.common.exception.MyException;
import com.wys.mcr.entity.Banner;
import com.wys.mcr.entity.Movie;
import com.wys.mcr.service.BannerService;
import com.wys.mcr.service.MovieService;
import com.wys.mcr.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @Author: lcw
 * @Date: 2019/6/6
 */
@Service
@Slf4j
public class UploadServiceImpl implements UploadService {
    @Value("${api.moviePath}")
    private String moviePath;

    @Value("${api.bannerPath}")
    private String bannerPath;

    @Value("${api.domainAddress}")
    private String domainAddress;

    @Autowired
    private MovieService movieService;

    @Autowired
    private BannerService bannerService;

//    @Override
//    public String batchUpload(HttpServletRequest request) {
//        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
//        MultipartFile file;
//        BufferedOutputStream stream;
//        for (int i = 0; i < files.size(); ++i) {
//            System.out.println("进来了");
//            file = files.get(i);
//            String filePath = "/data/m/";
//            if (!file.isEmpty()) {
//                try {
//                    System.out.println("文件不为空");
//                    byte[] bytes = file.getBytes();
//                    System.out.println("建立字节空间");
//                    stream = new BufferedOutputStream(new FileOutputStream(new File(filePath + file.getOriginalFilename())));
//                    System.out.println("开始写文件");
//                    stream.write(bytes);
//                    System.out.println("写完了");
//                    stream.close();
//                    System.out.println("关闭了");
//                } catch (Exception e) {
//                    throw new MyException("文件上传失败：" + i + " =>" + e.getMessage());
//                }
//            } else {
//                throw new MyException("文件上传失败：" + i + "文件为空");
//            }
//        }
//        return "upload successful";
//    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void upload(MultipartFile file, String title, String remark, String type) {
        if (file.isEmpty()) {
            throw new MyException("上传失败，请选择文件");
        }
        if (StringUtils.isEmpty(type)) {
            throw new MyException("上传类型不能为空");
        }

        switch (type) {
            case "movie":
                uploadFile(file, moviePath);
                Movie movie = new Movie();
                movie.setMovieTitle(title).setModifyDate(LocalDateTime.now()).setRemark(remark).setCreateDate(LocalDateTime.now()).setModifyId(ContextHolder.getUserId()).setCreateId(ContextHolder.getUserId()).setMovieUrl(domainAddress + "/m/" + file.getOriginalFilename());
                movieService.save(movie);
                log.info("上传成功");
                return;
            case "banner":
                uploadFile(file, bannerPath);
                Banner banner = new Banner();
                banner.setModifyDate(LocalDateTime.now()).setRemark(remark).setCreateDate(LocalDateTime.now()).setCreateId(ContextHolder.getUserId()).setUrl(domainAddress + "/p/" + file.getOriginalFilename());
                bannerService.save(banner);
                log.info("上传成功");
                return;
            default:
                throw new MyException("上传类型不存在");
        }
    }

    private void uploadFile(MultipartFile file, String filePath) {
        String fileName = file.getOriginalFilename();
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            log.error(e.toString(), e);
            throw new MyException("上传失败");
        }
        log.info("上传成功");
    }
}

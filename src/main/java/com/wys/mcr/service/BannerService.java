package com.wys.mcr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wys.mcr.common.dto.req.BannerReq;
import com.wys.mcr.entity.Banner;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lcw
 * @since 2019-05-27
 */
public interface BannerService extends IService<Banner> {

    IPage<Banner> queryPage(BannerReq bannerReq);

    void delete(BannerReq bannerReq);
}

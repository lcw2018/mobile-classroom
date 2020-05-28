package com.wys.mcr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wys.mcr.common.dto.req.BannerReq;
import com.wys.mcr.common.exception.MyException;
import com.wys.mcr.common.validator.ValidatorUtils;
import com.wys.mcr.entity.Banner;
import com.wys.mcr.mapper.BannerMapper;
import com.wys.mcr.service.BannerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lcw
 * @since 2019-05-27
 */
@Service
@Slf4j
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

    @Override
    public IPage<Banner> queryPage(BannerReq bannerReq) {
        ValidatorUtils.validateEntity(bannerReq);
        Page<Banner> bannerPage = new Page<>();
        bannerPage.setCurrent(bannerReq.getCurrent());
        bannerPage.setSize(bannerReq.getSize());
        LambdaQueryWrapper<Banner> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Banner::getDeleted, 0);
        return page(bannerPage, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(BannerReq bannerReq) {
        if (StringUtils.isEmpty(bannerReq.getId())) {
            throw new MyException("id不能为空");
        }
        Banner banner = getById(bannerReq.getId());
        if (null == banner) {
            throw new MyException("banner不存在，id：" + bannerReq.getId());
        }
        banner.setDeleted(1);
        LambdaUpdateWrapper<Banner> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Banner::getId, banner.getId());
        update(banner, updateWrapper);
//        log.info(JSO);
    }
}

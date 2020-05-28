package com.wys.mcr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wys.mcr.common.dto.req.CheckReq;
import com.wys.mcr.entity.Points;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lcw
 * @since 2019-06-30
 */
public interface PointsService extends IService<Points> {

    Integer getTotalPoints(CheckReq checkReq);
}

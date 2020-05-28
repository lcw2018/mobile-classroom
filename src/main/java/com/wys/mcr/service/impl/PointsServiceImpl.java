package com.wys.mcr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wys.mcr.common.dto.req.CheckReq;
import com.wys.mcr.entity.Points;
import com.wys.mcr.mapper.PointsMapper;
import com.wys.mcr.service.PointsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lcw
 * @since 2019-06-30
 */
@Service
public class PointsServiceImpl extends ServiceImpl<PointsMapper, Points> implements PointsService {

    @Override
    public Integer getTotalPoints(CheckReq checkReq) {
        Integer points = baseMapper.getTotalPoints(checkReq.getUserId(), checkReq.getFromDate(), checkReq.getToDate());
        return points;
    }
}

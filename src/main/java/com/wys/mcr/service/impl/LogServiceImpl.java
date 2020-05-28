

package com.wys.mcr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wys.mcr.common.dto.req.LogReq;
import com.wys.mcr.common.validator.ValidatorUtils;
import com.wys.mcr.entity.Log;
import com.wys.mcr.mapper.LogMapper;
import com.wys.mcr.service.LogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;


@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

    @Override
    public IPage<Log> queryPage(LogReq logReq) {
        ValidatorUtils.validateEntity(logReq);

        Page<Log> logPage = new Page<>();
        logPage.setCurrent(logReq.getCurrent());
        logPage.setSize(logReq.getSize());
        LambdaQueryWrapper<Log> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(logReq.getUserName())) {
            queryWrapper.like(Log::getUsername, logReq.getUserName());
        }
        return page(logPage, queryWrapper);
    }
}

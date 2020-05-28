package com.wys.mcr.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wys.mcr.common.dto.req.CheckReq;
import com.wys.mcr.common.dto.resp.CheckResp;
import com.wys.mcr.common.utils.CheckUtils;
import com.wys.mcr.entity.PractiseCheck;
import com.wys.mcr.mapper.PractiseCheckMapper;
import com.wys.mcr.service.PractiseCheckService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lcw
 * @since 2019-06-07
 */
@Service
public class PractiseCheckServiceImpl extends ServiceImpl<PractiseCheckMapper, PractiseCheck> implements PractiseCheckService {

    @Override
    public IPage<CheckResp> firstPage(CheckReq checkReq) {
        return CheckUtils.firstPage(checkReq, "practiseCheck");
    }

    @Override
    public IPage<CheckResp> secondPage(CheckReq checkReq) {
        return CheckUtils.secondPage(checkReq, "practiseCheck");
    }
}

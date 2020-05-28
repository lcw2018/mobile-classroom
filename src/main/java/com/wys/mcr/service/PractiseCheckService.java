package com.wys.mcr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wys.mcr.common.dto.req.CheckReq;
import com.wys.mcr.common.dto.resp.CheckResp;
import com.wys.mcr.entity.PractiseCheck;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lcw
 * @since 2019-06-07
 */
public interface PractiseCheckService extends IService<PractiseCheck> {

    IPage<CheckResp> firstPage(CheckReq checkReq);

    IPage<CheckResp> secondPage(CheckReq checkReq);
}

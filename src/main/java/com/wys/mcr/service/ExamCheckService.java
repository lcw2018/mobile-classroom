package com.wys.mcr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wys.mcr.common.dto.req.CheckReq;
import com.wys.mcr.common.dto.resp.CheckResp;
import com.wys.mcr.common.dto.resp.ExamCheckOneResp;
import com.wys.mcr.common.dto.resp.ExamCheckSecondResp;
import com.wys.mcr.common.dto.resp.ExamCheckThirdResp;
import com.wys.mcr.entity.ExamCheck;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lcw
 * @since 2019-06-07
 */
public interface ExamCheckService extends IService<ExamCheck> {

    IPage<CheckResp> firstPage(CheckReq checkReq);

    IPage<CheckResp> secondPage(CheckReq checkReq);

    IPage<CheckResp> thirdPage(CheckReq checkReq);

    List<ExamCheckOneResp> firstList(CheckReq checkReq);

    List<ExamCheckSecondResp> secondList(CheckReq checkReq);

//    List<ExamCheckThirdResp> thirdList(CheckReq checkReq);
}

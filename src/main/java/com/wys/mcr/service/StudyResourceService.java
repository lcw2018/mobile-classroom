package com.wys.mcr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wys.mcr.common.dto.req.StudyResourceReq;
import com.wys.mcr.common.dto.resp.StudyResourceResp;
import com.wys.mcr.entity.StudyResource;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lcw
 * @since 2019-06-05
 */
public interface StudyResourceService extends IService<StudyResource> {

    void saveStudyResource(StudyResourceReq studyResourceReq);

    IPage<StudyResourceResp> page(StudyResourceReq studyResourceReq);

    void edit(StudyResourceReq studyResourceReq);

    void delete(StudyResourceReq studyResourceReq);
}

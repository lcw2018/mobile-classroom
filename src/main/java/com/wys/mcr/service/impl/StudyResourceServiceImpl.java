package com.wys.mcr.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wys.mcr.common.ContextHolder;
import com.wys.mcr.common.dto.req.StudyResourceReq;
import com.wys.mcr.common.dto.resp.StudyResourceResp;
import com.wys.mcr.common.exception.MyException;
import com.wys.mcr.common.utils.StringUtil;
import com.wys.mcr.common.validator.ValidatorUtils;
import com.wys.mcr.entity.StudyResource;
import com.wys.mcr.mapper.StudyResourceMapper;
import com.wys.mcr.service.StudyResourceService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lcw
 * @since 2019-06-05
 */
@Service
public class StudyResourceServiceImpl extends ServiceImpl<StudyResourceMapper, StudyResource> implements StudyResourceService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveStudyResource(StudyResourceReq studyResourceReq) {
        ValidatorUtils.validateEntity(studyResourceReq, StudyResourceReq.Save.class);
        StudyResource studyResource = new StudyResource();
        BeanUtils.copyProperties(studyResourceReq, studyResource);
        studyResource.setId(StringUtil.genUUID());
        studyResource.setCreateDate(LocalDateTime.now());
        studyResource.setCreateId(ContextHolder.getUserId());
        studyResource.setModifyDate(LocalDateTime.now());
        studyResource.setModifyId(ContextHolder.getUserId());
        save(studyResource);
    }

    @Override
    public IPage<StudyResourceResp> page(StudyResourceReq studyResourceReq) {
        ValidatorUtils.validateEntity(studyResourceReq, StudyResourceReq.Page.class);
        Page<StudyResourceResp> studyResourceRespPage = new Page<>(studyResourceReq.getCurrent(), studyResourceReq.getSize());
        List<StudyResourceResp> studyResourceRespList = baseMapper.page(studyResourceRespPage);
        studyResourceRespPage.setRecords(studyResourceRespList);

        return studyResourceRespPage;
    }

    @Override
    public void edit(StudyResourceReq studyResourceReq) {
        ValidatorUtils.validateEntity(studyResourceReq, StudyResourceReq.Edit.class);
        StudyResource studyResource = getById(studyResourceReq.getId());
        if (null == studyResource) {
            throw new MyException("学习资料不存在,id为：" + studyResourceReq.getId());
        }
        BeanUtils.copyProperties(studyResourceReq, studyResource);
        studyResource.setModifyDate(LocalDateTime.now());
        studyResource.setModifyId(ContextHolder.getUserId());
        updateById(studyResource);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(StudyResourceReq studyResourceReq) {
        if (StringUtils.isEmpty(studyResourceReq.getId())) {
            throw new MyException("id不能为空");
        }
        LambdaUpdateWrapper<StudyResource> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(StudyResource::getId, studyResourceReq.getId());
        StudyResource studyResource = getById(studyResourceReq.getId());
        studyResource.setDeleted(1);
        update(studyResource, updateWrapper);
    }


}

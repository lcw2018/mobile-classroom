package com.wys.mcr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wys.mcr.common.dto.resp.StudyResourceResp;
import com.wys.mcr.entity.StudyResource;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author lcw
 * @since 2019-06-05
 */
public interface StudyResourceMapper extends BaseMapper<StudyResource> {

    List<StudyResourceResp> page(Page<StudyResourceResp> checkRespPage);
}

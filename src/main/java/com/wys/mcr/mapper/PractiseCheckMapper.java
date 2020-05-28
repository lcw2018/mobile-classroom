package com.wys.mcr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wys.mcr.common.dto.resp.CheckResp;
import com.wys.mcr.entity.PractiseCheck;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author lcw
 * @since 2019-06-07
 */
public interface PractiseCheckMapper extends BaseMapper<PractiseCheck> {
    List<CheckResp> queryCheckFirstPage(Page<CheckResp> checkRespPage, @Param("likeName") String likeName, @Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);

    List<CheckResp> queryCheckSecondPage(Page<CheckResp> checkRespPage, @Param("thirdDeptId") String thirdDeptId, @Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);
}

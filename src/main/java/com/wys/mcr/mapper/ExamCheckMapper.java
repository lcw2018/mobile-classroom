package com.wys.mcr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wys.mcr.common.dto.resp.CheckResp;
import com.wys.mcr.common.dto.resp.ExamCheckOneResp;
import com.wys.mcr.common.dto.resp.ExamCheckSecondResp;
import com.wys.mcr.common.dto.resp.ExamCheckThirdResp;
import com.wys.mcr.entity.ExamCheck;
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
public interface ExamCheckMapper extends BaseMapper<ExamCheck> {

    List<CheckResp> queryCheckFirstPage(Page<CheckResp> checkRespPage, @Param("likeName") String likeName, @Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);

    List<CheckResp> queryCheckSecondPage(Page<CheckResp> checkRespPage, @Param("thirdDeptId") String thirdDeptId, @Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);

    List<CheckResp> queryCheckThirdPage(Page<CheckResp> checkRespPage, @Param("userId") String userId, @Param("currentDeptId") String currentDeptId, @Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);

    List<ExamCheckOneResp> queryCheckFirstList(@Param("likeName") String likeName, @Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);

    List<ExamCheckSecondResp> queryCheckSecondList(@Param("thirdDeptId") String thirdDeptId, @Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);

}

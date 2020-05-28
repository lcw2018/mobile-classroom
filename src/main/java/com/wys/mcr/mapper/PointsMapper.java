package com.wys.mcr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wys.mcr.entity.Points;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author    lcw
 * @since 2019-06-30
 */
public interface PointsMapper extends BaseMapper<Points> {

    Integer getTotalPoints(@Param("userId") String userId, @Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);
}

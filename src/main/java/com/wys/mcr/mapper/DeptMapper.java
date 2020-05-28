package com.wys.mcr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wys.mcr.entity.Dept;

import java.util.List;

/**
 * <p>
 * 部门管理 Mapper 接口
 * </p>
 *
 * @author lcw
 * @since 2019-05-28
 */
public interface DeptMapper extends BaseMapper<Dept> {

    void updateLevel(List<String> deptIdList, int levelChangeNum);
}

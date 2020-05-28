package com.wys.mcr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wys.mcr.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 根据用户名，查询系统用户
     */
    SysUser queryByUserName(String username);

}

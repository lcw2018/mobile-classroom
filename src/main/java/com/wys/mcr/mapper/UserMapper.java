package com.wys.mcr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wys.mcr.common.dto.resp.UserResp;
import com.wys.mcr.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户
 *
 * @author lcw
 */
public interface UserMapper extends BaseMapper<User> {

}

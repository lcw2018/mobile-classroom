package com.wys.mcr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wys.mcr.entity.Role;
import com.wys.mcr.mapper.RoleMapper;
import com.wys.mcr.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author lcw
 * @since 2019-06-08
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}

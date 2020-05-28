package com.wys.mcr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wys.mcr.common.dto.req.UserAddReq;
import com.wys.mcr.common.dto.req.UserReq;
import com.wys.mcr.common.dto.resp.UserResp;
import com.wys.mcr.entity.User;

/**
 * 用户
 *
 * @author lcw
 */
public interface UserService extends IService<User> {

//    void updateDeptId(String oldDeptId, String newDeptId);

    IPage<UserResp> page(UserReq userReq);

    void freezen(String userId);

    void reset(String userId);

    void add(UserAddReq userAddReq);

    void editRole(UserReq userReq);
}

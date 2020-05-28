package com.wys.mcr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Strings;
import com.wys.mcr.common.ContextHolder;
import com.wys.mcr.common.dto.req.UserAddReq;
import com.wys.mcr.common.dto.req.UserReq;
import com.wys.mcr.common.dto.resp.UserResp;
import com.wys.mcr.common.exception.MyException;
import com.wys.mcr.common.utils.Base64Utils;
import com.wys.mcr.common.utils.DeptUtils;
import com.wys.mcr.common.validator.ValidatorUtils;
import com.wys.mcr.entity.User;
import com.wys.mcr.mapper.UserMapper;
import com.wys.mcr.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lcw
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

//    @Override
//    public void updateDeptId(String oldDeptId, String newDeptId) {
//        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
//        updateWrapper.eq(User::getDeptId,oldDeptId);
//        User user = new User();
//        user.setDeptId(newDeptId);
//        baseMapper.update(user,updateWrapper);
//    }

    @Override
    public IPage<UserResp> page(UserReq userReq) {
        IPage<User> page = new Page<>();
        page.setCurrent(userReq.getCurrent());
        page.setSize(userReq.getSize());

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getDeleted, 0);
        if (!Strings.isNullOrEmpty(userReq.getUserId())) {
            queryWrapper.eq(User::getPoliceId, Base64Utils.Base64Encode(userReq.getUserId()));
        }
        if (!Strings.isNullOrEmpty(userReq.getName())) {
            queryWrapper.like(User::getName, userReq.getName());
        }
        IPage<User> userIPage = page(page, queryWrapper);
        List<UserResp> userRespList = new ArrayList<>();
        userIPage.getRecords().forEach(user -> {
            UserResp userResp = new UserResp();
            BeanUtils.copyProperties(user, userResp);
            userResp.setDeptName(DeptUtils.getFullDeptName(user.getDeptId()));
            userResp.setPoliceId(Base64Utils.Base64Decode(userResp.getPoliceId()));
            userResp.setMobile(Base64Utils.Base64Decode(userResp.getMobile()));
            userRespList.add(userResp);
        });
        IPage<UserResp> userRespIPage = new Page<>();
        BeanUtils.copyProperties(userIPage, userRespIPage, "records");
        userRespIPage.setRecords(userRespList);
        return userRespIPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void freezen(String userId) {
        if (!ContextHolder.getUserName().startsWith("admin")) {
            throw new MyException("非管理员，无法操作");
        }
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getPoliceId, Base64Utils.Base64Encode(userId));
        updateWrapper.set(User::getFreeze, 1);
        update(updateWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reset(String userId) {
        if (!ContextHolder.getUserName().startsWith("admin")) {
            throw new MyException("非管理员，无法操作");
        }
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getPoliceId, Base64Utils.Base64Encode(userId));
        updateWrapper.set(User::getPassword, "MTIzNDU2");
        update(updateWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(UserAddReq userAddReq) {
        //表单校验
        ValidatorUtils.validateEntity(userAddReq);

        User user = new User();
        // 对用policeId、password des解密后base64存入数据库
        String dbPoliceId = Base64Utils.Base64Encode(userAddReq.getPoliceId());
        String dbPassword = Base64Utils.Base64Encode(userAddReq.getPassword());
        if (StringUtils.isNotEmpty(userAddReq.getMobile())) {
            String dbMobile = Base64Utils.Base64Encode(userAddReq.getMobile());
            user.setMobile(dbMobile);
        }
        user.setPoliceId(dbPoliceId);
        user.setPassword(dbPassword);
        user.setName(userAddReq.getName());
        user.setDeptId(userAddReq.getDeptId());
        user.setCreateId(dbPoliceId);
        //给一个默认角色
        user.setRoleId(userAddReq.getRoleId());
        user.setCreateDate(LocalDateTime.now());
        user.setFreeze(0);
        user.setDeleted(0);
        save(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editRole(UserReq userReq) {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getPoliceId, Base64Utils.Base64Encode(userReq.getUserId()));
        updateWrapper.set(User::getRoleId, userReq.getRoleId());
        updateWrapper.set(User::getModifyId, ContextHolder.getUserId());
        updateWrapper.set(User::getModifyDate, LocalDateTime.now());
        update(updateWrapper);
    }
}

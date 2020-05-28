

package com.wys.mcr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wys.mcr.common.ContextHolder;
import com.wys.mcr.common.dto.req.SysUserReq;
import com.wys.mcr.common.dto.resp.LoginResp;
import com.wys.mcr.common.exception.MyException;
import com.wys.mcr.common.utils.PageUtils;
import com.wys.mcr.common.validator.Assert;
import com.wys.mcr.entity.SysUser;
import com.wys.mcr.entity.Token;
import com.wys.mcr.form.LoginForm;
import com.wys.mcr.form.PasswordForm;
import com.wys.mcr.mapper.SysUserMapper;
import com.wys.mcr.service.SysUserService;
import com.wys.mcr.service.TokenService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    private TokenService tokenService;

    @Override
    public PageUtils queryPage(SysUserReq userReq) {

        IPage<SysUser> page = new Page<>();
        page.setPages(userReq.getCurrent());
        page.setSize(userReq.getSize());

        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        IPage<SysUser> userIPage = page(page, queryWrapper);
        return new PageUtils(userIPage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUser(SysUser user) {
        user.setCreateDate(LocalDateTime.now());
        //加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setPassword(DigestUtils.sha256Hex(user.getPassword() + salt));
        user.setSalt(salt);
        this.save(user);
    }

    @Override
    public SysUser queryByUserName(String username) {
        return baseMapper.queryByUserName(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysUser user) {
        if (StringUtils.isBlank(user.getPassword())) {
            user.setPassword(null);
        } else {
            user.setPassword(DigestUtils.sha256Hex(user.getPassword() + user.getSalt()));
        }
        this.updateById(user);
    }

    @Override
    public boolean updatePassword(String userId, String password, String newPassword) {
        SysUser user = new SysUser();
        user.setPassword(newPassword);
        return this.update(user,
                new QueryWrapper<SysUser>().eq("user_id", userId).eq("password", password));
    }

    @Override
    public void editPassword(PasswordForm form) {
        Assert.isBlank(form.getNewPassword(), "新密码不为能空");
        SysUser sysUser = getCurrentUser();

        //sha256加密
        String password = DigestUtils.sha256Hex(form.getPassword() + getCurrentUser().getSalt());
        if (!password.equals(sysUser.getPassword())) {
            throw new MyException("原密码错误");
        }
        //sha256加密
        String newPassword = DigestUtils.sha256Hex(form.getPassword() + getCurrentUser().getSalt());

        //更新密码
        sysUser.setPassword(newPassword);
        baseMapper.updateById(sysUser);
    }

    @Override
    public SysUser getCurrentUser() {
        String userId = ContextHolder.getUserId();
        SysUser userEntity = baseMapper.selectById(userId);

        return userEntity;
    }

    @Override
    public Map<String, Object> login(LoginForm form) {
        //用户信息
        SysUser user = queryByUserName(form.getUsername());

        //账号不存在、密码错误
        if (user == null || !user.getPassword().equals(DigestUtils.sha256Hex(form.getPassword() + user.getSalt()))) {
            throw new MyException("账号或密码不正确");
        }

        //账号锁定
        if (user.getStatus() == 1) {
            throw new MyException("账号已被锁定,请联系管理员");
        }

        //生成token，并保存到数据库
        Token token = tokenService.createToken(user.getUserId());
        LoginResp loginResp = new LoginResp();
        loginResp.setToken(token.getToken());
        loginResp.setUserId(user.getUserId());
        loginResp.setName(user.getUsername());
        loginResp.setMobile(user.getMobile());
        Map<String, Object> map = new HashMap<>(1);
        map.put("data", loginResp);
        return map;
    }

    @Override
    public void logout() {
        tokenService.logout(getCurrentUser().getUserId());
    }
}
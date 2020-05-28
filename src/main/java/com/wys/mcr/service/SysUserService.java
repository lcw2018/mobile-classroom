

package com.wys.mcr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wys.mcr.common.dto.req.SysUserReq;
import com.wys.mcr.common.utils.PageUtils;
import com.wys.mcr.entity.SysUser;
import com.wys.mcr.form.LoginForm;
import com.wys.mcr.form.PasswordForm;

import java.util.Map;


/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysUserService extends IService<SysUser> {

    PageUtils queryPage(SysUserReq userReq);

    /**
     * 根据用户名，查询系统用户
     */
    SysUser queryByUserName(String username);

    /**
     * 保存用户
     */
    void saveUser(SysUser user);

    /**
     * 修改用户
     */
    void update(SysUser user);

    /**
     * 修改密码
     *
     * @param userId      用户ID
     * @param password    原密码
     * @param newPassword 新密码
     */
    boolean updatePassword(String userId, String password, String newPassword);


    void editPassword(PasswordForm form);

    SysUser getCurrentUser();

    Map<String, Object> login(LoginForm form);

    void logout();
}

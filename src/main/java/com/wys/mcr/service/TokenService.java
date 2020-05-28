

package com.wys.mcr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wys.mcr.common.utils.R;
import com.wys.mcr.entity.Token;

/**
 * 用户Token
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface TokenService extends IService<Token> {

    /**
     * 生成token
     *
     * @param userId 用户ID
     */
    Token createToken(String userId);

    /**
     * 退出，修改token值
     *
     * @param userId 用户ID
     */
    void logout(String userId);

    Token queryByToken(String token);

}

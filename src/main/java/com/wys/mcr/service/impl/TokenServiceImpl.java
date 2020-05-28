

package com.wys.mcr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wys.mcr.common.utils.TokenGenerator;
import com.wys.mcr.entity.Token;
import com.wys.mcr.mapper.TokenMapper;
import com.wys.mcr.service.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


/**
 * @author lcw
 */
@Service("sysUserTokenService")
public class TokenServiceImpl extends ServiceImpl<TokenMapper, Token> implements TokenService {
    @Value("${api.expire}")
    private Integer expire;


    @Override
    public Token createToken(String userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();

        //过期时间
        LocalDateTime expireTime = LocalDateTime.now().plusHours(expire);

        Token tokenEntity = new Token();
        tokenEntity.setUserId(userId);
        tokenEntity.setExpireTime(expireTime);
        tokenEntity.setToken(token);
        tokenEntity.setUpdateTime(LocalDateTime.now());
        this.saveOrUpdate(tokenEntity);

        return tokenEntity;
    }

    @Override
    public void logout(String userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();

        //修改token
        Token tokenEntity = new Token();
        tokenEntity.setUserId(userId);
        tokenEntity.setToken(token);
        this.updateById(tokenEntity);
    }

    @Override
    public Token queryByToken(String token) {
        return this.getOne(new QueryWrapper<Token>().eq("token", token));
    }
}

package com.wys.mcr;

import com.wys.mcr.common.utils.RedisUtils;
import com.wys.mcr.entity.SysUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void contextLoads() {
//        SysUser user = new SysUser();
//        user.setEmail("qqq@qq.com");
//        redisUtils.set("user", user);
//
//        System.out.println(ToStringBuilder.reflectionToString(redisUtils.get("user", SysUser.class)));

        //String s = DigestUtils.sha256Hex("hlddlb123," + "YzcmCZNvbXocrsz9dm8e");
        String s = DigestUtils.sha256Hex("lcw" + "aaa");
        System.out.println(s);
    }

}

package com.wys.mcr.common.utils;

import java.util.UUID;

/**
 * @Author: lcw
 * @Date: 2019/6/8
 */
public class StringUtil {
    public static String genUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

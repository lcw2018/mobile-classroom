package com.wys.mcr.common;

/**
 * @author chenyuanhui
 */
public class ContextHolder {
    private static final ThreadLocal<String> userIdHolder = new ThreadLocal();
    private static final ThreadLocal<String> userNameHolder = new ThreadLocal();

    public ContextHolder() {
    }

    public static String getUserId() {
        return userIdHolder.get();
    }

    public static void setUserId(String userId) {
        userIdHolder.set(userId);
    }

    public static String getUserName() {
        return userNameHolder.get();
    }

    public static void setUserName(String userName) {
        userNameHolder.set(userName);
    }

}
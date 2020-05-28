package com.wys.mcr.common.enums;

/**
 * @author lcw
 */
public enum ResponseCodeEnum {
    // 系统
    UNKNOW_EXCEPTION(500, "未知异常，请联系管理员"),

    CODE_ERROR(500, "数据解析错误"),

    SUCCESS(200, "success"),

    // 用户
    SAVE_OPERATELOG_FAIL(500, "日志保存失败"),

    DUPLICATE_KEY(500, "数据库中已存在该记录"),

    TOKEN_INVALID(501, "token失效，请重新登录"),

    PASSWORD_ERROR(500, "原始密码错误");

    private Integer code;
    private String message;

    ResponseCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public final Integer getCode() {
        return this.code;
    }

    public final String getMessage() {
        return this.message;
    }

}
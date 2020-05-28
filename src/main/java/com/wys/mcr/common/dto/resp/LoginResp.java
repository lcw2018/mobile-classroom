package com.wys.mcr.common.dto.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: lcw
 * @Date: 2019/5/29
 */
@Data
public class LoginResp {
    private String userId;

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "token")
    private String token;
}

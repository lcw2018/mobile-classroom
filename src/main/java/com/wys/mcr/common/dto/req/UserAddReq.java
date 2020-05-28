package com.wys.mcr.common.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: lcw
 * @Date: 2019/6/5
 */
@Data
public class UserAddReq {

    @NotBlank(message = "警号不能为空！")
    private String policeId;

    @NotBlank(message = "姓名不能为空！")
    private String name;

    @NotBlank(message = "密码不能为空")
    private String password;

    private String mobile;

    @NotBlank(message = "部门不能为空")
    private String deptId;

    @NotBlank(message = "角色不能为空")
    @ApiModelProperty(value = "角色id")
    private String roleId;
}

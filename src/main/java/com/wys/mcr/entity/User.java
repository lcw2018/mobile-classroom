package com.wys.mcr.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户
 *
 * @author lcw
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "User对象", description = "用户")
@TableName("app_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    @ApiModelProperty(value = "警号")
    private String policeId;

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ApiModelProperty(value = "密码---只可以序列化，不可反序列化，即不会返回该字段")
    private String password;

    @ApiModelProperty(value = "部门id")
    private String deptId;

    @ApiModelProperty(value = "头像url")
    private String txUrl;

    @ApiModelProperty(value = "是否冻结（未冻结：0）", example = "0")
    private Integer freeze;

    @ApiModelProperty(value = "角色id")
    private String roleId;

    @ApiModelProperty(value = "创建人id")
    private String createId;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "修改人id")
    private String modifyId;

    @ApiModelProperty(value = "修改日期")
    private LocalDateTime modifyDate;

    private Integer deleted;
}

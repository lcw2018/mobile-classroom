package com.wys.mcr.common.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: lcw
 * @Date: 2019/5/28
 */
@Data
public class DeptReq {

    @NotBlank(groups = {Update.class,Deleted.class})
    @ApiModelProperty(value = "部门id")
    private String deptId;

    @NotBlank(groups = {Save.class, Update.class})
    @ApiModelProperty(value = "部门名称")
    private String name;

    @NotBlank(groups = {Save.class, Update.class})
    @ApiModelProperty(value = "父类id")
    private String parentId;

    @NotNull(groups = {Save.class, Update.class})
    @ApiModelProperty(value = "排序", example = "12")
    private Integer sortOrder;

    @NotNull(groups = {Save.class, Update.class})
    @ApiModelProperty(value = "部门级别", example = "1")
    private Integer level;

    public interface Save {
    }

    public interface Update {
    }

    public interface Deleted {
    }
}

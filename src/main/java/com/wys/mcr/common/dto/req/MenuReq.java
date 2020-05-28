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
public class MenuReq {

    @NotBlank(groups = {Update.class})
    private String id;

    @NotBlank(groups = {Save.class, Update.class})
    @ApiModelProperty(value = "菜单名称")
    private String name;

    @NotBlank(groups = {Save.class, Update.class})
    @ApiModelProperty(value = "父菜单ID")
    private String parentId;

    @ApiModelProperty(value = "描述")
    private String remark;

    @NotBlank(groups = {Save.class, Update.class})
    @ApiModelProperty(value = "类型  1：学   2：练 3：考")
    private String type;

    @NotNull(groups = {Save.class, Update.class})
    @ApiModelProperty(value = "菜单级别", example = "1")
    private Integer level;

    @ApiModelProperty(value = "图片url")
    private String tpUrl;

    public interface Save {
    }

    public interface Update {
    }
}

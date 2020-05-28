package com.wys.mcr.common.dto.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: lcw
 * @Date: 2019/5/28
 */
@Data
public class MenuResp {

    private String id;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "描述")
    private String remark;

    @ApiModelProperty(value = "图片url")
    private String tpUrl;

    @ApiModelProperty(value = "父菜单ID")
    private String parentId;

    @ApiModelProperty(value = "类型  1：学   2：练 3：考")
    private String type;

    @ApiModelProperty(value = "类型  1：学   2：练 3：考")
    private Integer level;

    @ApiModelProperty(value = "排序", example = "12")
    private Integer order;
}

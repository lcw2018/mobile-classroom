package com.wys.mcr.common.dto.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 部门管理
 *
 * @author lcw
 * @since 2019-05-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DeptResp {

    @ApiModelProperty(value = "部门id")
    private String deptId;

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "父类id")
    private String parentId;

    @ApiModelProperty(value = "排序", example = "12")
    private Integer sortOrder;

    @ApiModelProperty(value = "层级", example = "1")
    private Integer level;

}

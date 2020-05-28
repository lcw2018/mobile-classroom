package com.wys.mcr.common.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: lcw
 * @Date: 2019/5/28
 */
@Data
public class ExcelReq {

    @NotNull
    @ApiModelProperty(value = "类型（1、单选 2、多选 3、判断）", example = "1")
    private Integer type;
}

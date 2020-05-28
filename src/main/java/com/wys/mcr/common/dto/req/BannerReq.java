package com.wys.mcr.common.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: lcw
 * @Date: 2019/6/5
 */
@Data
public class BannerReq {

    private String id;

    @NotNull
    @ApiModelProperty(value = "当前页", example = "1")
    private Integer current;

    @ApiModelProperty(value = "每页数量", example = "10")
    private Integer size = 10;

}

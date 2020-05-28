package com.wys.mcr.common.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @Author: lcw
 * @Date: 2019/5/28
 */
@Data
public class CheckReq {

    @ApiModelProperty(value = "部门id")
    private String deptId;

    @ApiModelProperty(value = "部门模糊查询名称")
    private String likeName;

    @ApiModelProperty(value = "查询开始日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDate;

    @ApiModelProperty(value = "查询结束日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate toDate;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "当前部门id")
    private String currentDeptId;

    @NotNull
    @ApiModelProperty(value = "当前页", example = "1")
    private Integer current;

    @ApiModelProperty(value = "每页数量", example = "10")
    private Integer size = 10;
}

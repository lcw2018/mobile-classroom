package com.wys.mcr.common.dto.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author lcw
 * @since 2019-05-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CheckResp {

    private String id;

    @ApiModelProperty(value = "部门id")
    private String deptId;

    @ApiModelProperty(value = "二级部门id")
    private String secondDeptId;

    @ApiModelProperty(value = "部门名称")
    private String deptName;

    @ApiModelProperty(value = "次数", example = "10")
    private Integer count;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "考核日期")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "得分", example = "100")
    private Integer score;

    @ApiModelProperty(value = "当前部门id")
    private String currentDeptId;

    @ApiModelProperty(value = "积分", example = "100")
    private Integer points;

    @ApiModelProperty(value = "答题时长（分钟）", example = "10")
    private Integer duration;

}

package com.wys.mcr.common.dto.resp;

import com.wys.mcr.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author lcw
 * @since 2019-05-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Excel("二级考核列表导出")
public class ExamCheckSecondResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名称")
    @Excel("姓名")
    private String userName;

    @ApiModelProperty(value = "用户id")
    @Excel("警号")
    private String userId;

    @ApiModelProperty(value = "用户当前部门id")
    private String deptId;

    @ApiModelProperty(value = "部门名称")
    @Excel("部门名称")
    private String deptName;

    @ApiModelProperty(value = "积分", example = "100")
    @Excel("积分")
    private Integer points;

    @ApiModelProperty(value = "次数", example = "10")
    @Excel("次数")
    private Integer count;

    private String currentDeptId;
}

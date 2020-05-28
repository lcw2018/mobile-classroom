package com.wys.mcr.common.dto.resp;

import com.wys.mcr.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author lcw
 * @since 2019-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "PractiseCheck对象", description = "")
@Excel("一级考核列表导出")
public class ExamCheckOneResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "部门名称")
    @Excel("部门名称")
    private String deptName;

    @ApiModelProperty(value = "积分", example = "100")
    @Excel("积分")
    private Integer points;

    @ApiModelProperty(value = "次数", example = "10")
    @Excel("次数")
    private Integer count;

    @ApiModelProperty(value = "二级部门id")
    private String secondDeptId;

}

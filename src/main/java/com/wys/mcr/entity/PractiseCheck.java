package com.wys.mcr.entity;

import com.wys.mcr.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class PractiseCheck implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "记录生成时的二级部门id")
    private String secondDeptId;

    @ApiModelProperty(value = "记录生成时的三级部门id")
    private String thirdDeptId;

    @ApiModelProperty(value = "记录生成时的部门id")
    private String currentDeptId;

    private Integer level;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "创建人id")
    private String createId;

    @ApiModelProperty(value = "修改日期")
    private LocalDateTime modifyDate;

    @ApiModelProperty(value = "修改人id")
    private String modifyId;

    @ApiModelProperty(value = "是否删除（0：正常）")
    private Integer deleted;


}

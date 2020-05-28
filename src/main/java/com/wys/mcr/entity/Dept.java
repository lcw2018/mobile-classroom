package com.wys.mcr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 部门管理
 *
 * @author lcw
 * @since 2019-05-28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Dept对象", description = "部门管理")
@TableName("app_dept")
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "部门id")
    @TableId(type = IdType.UUID)
    private String deptId;

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "父类id")
    private String parentId;

    @ApiModelProperty(value = "部门级别")
    private Integer level;

    @ApiModelProperty(value = "排序", example = "12")
    private Integer sortOrder;

    @ApiModelProperty(value = "创建日期")
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createDate;

    @ApiModelProperty(value = "创建人Id")
    private String createId;

    private String modifyId;

    private LocalDateTime modifyDate;

    @ApiModelProperty(value = "是否删除  1：已删除  0：正常", example = "0")
    private Integer deleted;
}

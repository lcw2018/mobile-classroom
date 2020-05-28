package com.wys.mcr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lcw
 * @since 2019-05-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Banner对象", description = "")
@TableName("app_banner")
public class Banner implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.UUID)
    @ApiModelProperty(value = "banner图Id")
    private String id;

    @ApiModelProperty(value = "图片地址")
    private String url;

    @ApiModelProperty(value = "图片描述")
    private String remark;

    private String createId;

    private LocalDateTime createDate;

    private String modifyId;

    private LocalDateTime modifyDate;

    private Integer deleted;


}

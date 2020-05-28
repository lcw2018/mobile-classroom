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
 * <p>
 *
 * </p>
 *
 * @author lcw
 * @since 2019-05-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Movie对象", description = "")
@TableName("app_movie")
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.UUID)
    @ApiModelProperty(value = "视频Id")
    private String id;

    @ApiModelProperty(value = "视频标题")
    private String movieTitle;

    @ApiModelProperty(value = "视频url")
    private String movieUrl;

    @ApiModelProperty(value = "视频描述")
    private String remark;

    private String createId;

    private LocalDateTime createDate;

    private String modifyId;

    private LocalDateTime modifyDate;

    private Integer deleted;


}

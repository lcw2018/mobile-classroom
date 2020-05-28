package com.wys.mcr.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
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
 * @since 2019-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("app_studyResource")
@ApiModel(value = "StudyResource对象", description = "")
public class StudyResource implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String content;

    private String name;

    private String menuId;

    private LocalDateTime createDate;

    private String createId;

    private LocalDateTime modifyDate;

    private String modifyId;

    private Integer deleted;

}

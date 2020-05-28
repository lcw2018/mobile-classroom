package com.wys.mcr.common.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author: lcw
 * @Date: 2019/6/8
 */
@Data
public class StudyResourceReq implements Serializable {
    @NotNull(message = "学习资料id不能为空", groups = {Edit.class})
    @ApiModelProperty(value = "菜单id")
    private String id;

    @NotNull(message = "菜单id不能为空", groups = {Save.class, Edit.class})
    @ApiModelProperty(value = "菜单id")
    private String menuId;

    @NotNull(message = "学习资料标题不能为空", groups = {Save.class, Edit.class})
    @ApiModelProperty(value = "学习资料标题")
    private String name;

    @NotNull(message = "内容不能为空", groups = {Save.class, Edit.class})
    private String content;

    @NotNull(groups = {Page.class})
    @ApiModelProperty(value = "当前页", example = "1")
    private Integer current;

    @ApiModelProperty(value = "每页数量", example = "10")
    private Integer size = 10;

    public interface Save {

    }

    public interface Page {

    }

    public interface Edit {

    }


}

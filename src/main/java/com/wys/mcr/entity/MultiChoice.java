package com.wys.mcr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wys.mcr.common.annotation.Excel;
import com.wys.mcr.common.enums.ExcelType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author lcw
 * @since 2019-05-29
 */
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "MultiChoice对象", description = "")
@TableName("app_multiChoice")
@Excel("多选题")
public class MultiChoice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.UUID)
    @ApiModelProperty(value = "Id")
    private String id;

    @Excel(value = "题目", type = ExcelType.IMPORT)
    @ApiModelProperty(value = "题目")
    private String title;

    @Excel(value = "选项", type = ExcelType.IMPORT)
    @ApiModelProperty(value = "选项")
    private String optional;

    @ApiModelProperty(value = "菜单Id")
    private String menuId;

    @Excel(value = "答案", type = ExcelType.IMPORT)
    @ApiModelProperty(value = "答案")
    private String answer;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "是否删除（0：正常，1：删除）", example = "0")
    private Integer deleted;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOptional() {
        return optional;
    }

    public void setOptional(String optional) {
        this.optional = optional;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

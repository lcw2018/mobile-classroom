package com.wys.mcr.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value = "MenuType对象", description = "")
@TableName(value = "app_menuType")
public class MenuType implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String typeId;

    private String typeName;


}

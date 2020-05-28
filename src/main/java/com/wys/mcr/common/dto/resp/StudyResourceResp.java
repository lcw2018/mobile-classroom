package com.wys.mcr.common.dto.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author lcw
 * @since 2019-05-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StudyResourceResp {

    @ApiModelProperty(value = "学习资料id")
    private String id;

    @ApiModelProperty(value = "学习资料名称")
    private String name;

    @ApiModelProperty(value = "修改日期")
    private LocalDateTime modifyDate;

}

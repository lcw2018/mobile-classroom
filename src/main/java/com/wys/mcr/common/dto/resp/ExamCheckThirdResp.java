package com.wys.mcr.common.dto.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author lcw
 * @since 2019-05-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ExamCheckThirdResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "积分", example = "100")
    private Integer points;

    @ApiModelProperty(value = "得分", example = "100")
    private Integer score;

    @ApiModelProperty(value = "答题时长（分钟）", example = "10")
    private Integer duration;

}

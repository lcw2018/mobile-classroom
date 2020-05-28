package com.wys.mcr.common.dto.resp;

import com.wys.mcr.entity.User;
import lombok.Data;

/**
 * @Author: lcw
 * @Date: 2019/5/28
 */
@Data
public class UserResp extends User {

    private String deptName;
}

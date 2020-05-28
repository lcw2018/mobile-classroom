

package com.wys.mcr.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wys.mcr.common.dto.req.LogReq;
import com.wys.mcr.entity.Log;


/**
 * 系统日志
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface LogService extends IService<Log> {

    IPage<Log> queryPage(LogReq params);

}

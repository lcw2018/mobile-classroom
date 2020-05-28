

package com.wys.mcr.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wys.mcr.common.annotation.Login;
import com.wys.mcr.common.dto.req.LogReq;
import com.wys.mcr.common.utils.R;
import com.wys.mcr.entity.Log;
import com.wys.mcr.service.LogService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 系统日志
 *
 * @author Mark sunlightcs@gmail.com
 */
@Api(tags = "日志接口")
@Controller
@RequestMapping("/sys/log")
public class LogController {
    @Autowired
    private LogService logService;

    /**
     * 列表
     */
    @ResponseBody
    @Login
    @GetMapping("/page")
    public R page(LogReq logReq) {
        IPage<Log> page = logService.queryPage(logReq);

        return R.ok().put("data", page);
    }

}

package com.wys.mcr.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wys.mcr.common.annotation.Login;
import com.wys.mcr.common.annotation.SysLog;
import com.wys.mcr.common.dto.req.BannerReq;
import com.wys.mcr.common.utils.R;
import com.wys.mcr.entity.Banner;
import com.wys.mcr.service.BannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lcw
 * @since 2019-05-27
 */
@Api(tags = "banner接口")
@RestController
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @ApiOperation("banner列表分页查询")
    @Login
    @GetMapping("queryPage")
    public R queryPage(BannerReq bannerReq) {
        IPage<Banner> bannerIPage = bannerService.queryPage(bannerReq);
        return R.ok().put("data", bannerIPage);
    }

    @ApiOperation("banner图删除")
    @SysLog("banner图删除")
    @Login
    @PostMapping("/delete")
    public R delete(@RequestBody BannerReq bannerReq) {
        bannerService.delete(bannerReq);
        return R.ok();
    }
}


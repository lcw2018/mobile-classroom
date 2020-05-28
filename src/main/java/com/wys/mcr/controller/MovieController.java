package com.wys.mcr.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wys.mcr.common.annotation.Login;
import com.wys.mcr.common.annotation.SysLog;
import com.wys.mcr.common.dto.req.MovieReq;
import com.wys.mcr.common.utils.R;
import com.wys.mcr.entity.Movie;
import com.wys.mcr.service.MovieService;
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
@Api(tags = "视频接口")
@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @ApiOperation("视屏分页查询")
    @Login
    @GetMapping("queryPage")
    public R queryPage(MovieReq movieReq) {
        IPage<Movie> movieIPage = movieService.queryPage(movieReq);
        return R.ok().put("data", movieIPage);
    }

    @ApiOperation("视频删除")
    @SysLog("视频删除")
    @Login
    @PostMapping("/delete")
    public R delete(@RequestBody MovieReq movieReq) {
        movieService.delete(movieReq);

        return R.ok();
    }

}


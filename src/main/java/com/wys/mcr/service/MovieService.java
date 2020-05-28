package com.wys.mcr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wys.mcr.common.dto.req.MovieReq;
import com.wys.mcr.entity.Movie;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lcw
 * @since 2019-05-27
 */
public interface MovieService extends IService<Movie> {

    IPage<Movie> queryPage(MovieReq movieReq);

    void delete(MovieReq movieReq);
}

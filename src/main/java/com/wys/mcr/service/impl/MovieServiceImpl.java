package com.wys.mcr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wys.mcr.common.dto.req.MovieReq;
import com.wys.mcr.common.exception.MyException;
import com.wys.mcr.common.validator.ValidatorUtils;
import com.wys.mcr.entity.Movie;
import com.wys.mcr.mapper.MovieMapper;
import com.wys.mcr.service.MovieService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lcw
 * @since 2019-05-27
 */
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements MovieService {

    @Override
    public IPage<Movie> queryPage(MovieReq movieReq) {
        ValidatorUtils.validateEntity(movieReq);
        Page<Movie> moviePage = new Page<>();
        moviePage.setCurrent(movieReq.getCurrent());
        moviePage.setSize(movieReq.getSize());
        LambdaQueryWrapper<Movie> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Movie::getDeleted, 0);
        return page(moviePage, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(MovieReq movieReq) {
        if (StringUtils.isEmpty(movieReq.getId())) {
            throw new MyException("id不能为空");
        }
        Movie movie = getById(movieReq.getId());
        movie.setDeleted(1);
        LambdaUpdateWrapper<Movie> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Movie::getId, movieReq.getId());
        update(movie, updateWrapper);
    }
}

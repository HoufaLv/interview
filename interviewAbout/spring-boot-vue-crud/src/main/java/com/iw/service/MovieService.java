package com.iw.service;

import com.github.pagehelper.PageInfo;
import com.iw.entity.Movie;

import java.util.List;

/**
 *
 */
public interface MovieService {
    /**
     * 根据页号查询出所有Movie 数据
     * @param pageNo
     * @return
     */
    PageInfo<Movie> selectByPageNo(Integer pageNo);

    /**
     * 查询所有Movie 信息
     * @return
     */
    List<Movie> listMovie();

    /**
     * 新增Movie
     * @param movie
     */
    void insertMovie(Movie movie);
}

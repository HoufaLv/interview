package com.iw.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iw.entity.Movie;
import com.iw.entity.MovieExample;
import com.iw.mapper.MovieMapper;
import com.iw.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * MovieService 实现类
 */
@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    /**
     * 根据页号查询出所有Movie 数据
     *
     * @param pageNo
     * @return
     */
    @Override
    public PageInfo<Movie> selectByPageNo(Integer pageNo) {
        PageHelper.startPage(pageNo,10);
        List<Movie> movieList = movieMapper.selectByExample(new MovieExample());

        return new PageInfo<>(movieList);
    }

    /**
     * 查询所有Movie 信息
     *
     * @return
     */
    @Override
    public List<Movie> listMovie() {
        List<Movie> movieList = movieMapper.selectByExample(new MovieExample());
        return movieList;
    }

    /**
     * 新增Movie
     *
     * @param movie
     */
    @Override
    public void insertMovie(Movie movie) {
        movieMapper.insertSelective(movie);
    }
}

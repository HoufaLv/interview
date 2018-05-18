package com.iw.controller;

import com.github.pagehelper.PageInfo;
import com.iw.controller.result.ResponseBean;
import com.iw.entity.Movie;
import com.iw.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Movie 业务控制器
 */
@RestController
@RequestMapping("/movie")
@CrossOrigin("*")
public class MovieController {
    // TODO: 2018/5/17 0017 向前端封装正确的 ResponseBean

    @Autowired
    private MovieService movieService;

//    /**
//     * 根据页号来查询出来全部数据
//     * @param pageNo
//     * @return
//     */
//    @GetMapping
//    public ResponseBean listMovie(@RequestParam(required = false,name = "p",defaultValue = "1") Integer pageNo){
//        PageInfo<Movie> moviePageInfo = movieService.selectByPageNo(pageNo);
//
//        return  ResponseBean.success(moviePageInfo);
//    }


    @GetMapping
    public ResponseBean listMovie() {
        List<Movie> movieList = movieService.listMovie();

        return ResponseBean.success(movieList);
    }

    /**
     * 新增Movie
     * @param movie
     * @return
     */
    @PostMapping("/new")
    public ResponseBean addMovie(@RequestBody Movie movie) {
        System.out.println(movie);
        //movieService.insertMovie(movie);
        return ResponseBean.success();
    }

}

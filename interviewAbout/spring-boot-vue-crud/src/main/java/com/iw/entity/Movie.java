package com.iw.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class Movie implements Serializable {
    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rate=" + rate +
                ", releaseYear='" + releaseYear + '\'' +
                ", sendTime='" + sendTime + '\'' +
                ", director='" + director + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    private Integer id;

    /**
     * 电影名称
     */
    private String title;

    /**
     * 评分
     */
    private Float rate;

    private String releaseYear;

    private String sendTime;

    private String director;

    private String description;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
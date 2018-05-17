package com.iw.tms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 返回ajax 结果对象
 * @author Lvhoufa
 */
@JsonInclude
public class ResponseBean {
    //@JsonInclude(Include.NON_NULL) 这个注解主要用来解决返回前端数据为null 的情况
    //实体类与json互转的时候 属性值为null的不参与序列化
    // TODO: 2018/5/1 0001 设计一个工具类来向前端传递数据


    /**
     * 封装三个基本字段,状态,信息和数据,用来向前端传递数据
     */
    private String status;
    private String message;
    private Object data;


    public static ResponseBean error(String message){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setStatus("error");
        responseBean.setMessage(message);
        return  responseBean;
    }

    public static ResponseBean success(){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setStatus("success");
        return responseBean;
    }

    public static ResponseBean success(Object data){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setStatus("success");
        responseBean.setData(data);
        return responseBean;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

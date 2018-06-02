package com.ksit.tms.com.ksit.tms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * AjaxResult 类
 * @author Lvhoufa
 */
@JsonInclude(JsonInclude.Include.NON_NULL) //如果为null,则显示为空,不显示为null
public class ResponseBean {

    private String status;
    private String message;
    private Object data;

    public static ResponseBean error(String message) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setStatus("error");
        responseBean.setMessage(message);
        return responseBean;
    }

    public static ResponseBean success() {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setStatus("success");
        return responseBean;
    }

    public static ResponseBean success(Object data) {
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

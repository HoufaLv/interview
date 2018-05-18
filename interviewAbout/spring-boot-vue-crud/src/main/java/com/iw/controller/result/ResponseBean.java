package com.iw.controller.result;

/**
 * 响应ajax 请求
 */
public class ResponseBean {

    private static final String RESULT_SUCCESS = "success";
    private static final String RESULT_ERROR = "error";

    private String state;
    private Object result;
    private String message;

    /**
     * 请求成功
     * @param result    返回结果对象
     * @return
     */
    public static ResponseBean success(Object result){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setState(RESULT_SUCCESS);
        responseBean.setResult(result);
        return responseBean;
    }
    public static ResponseBean success(){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setState(RESULT_SUCCESS);
        return responseBean;
    }

    /**
     * 响应失败
     * @return
     */
    public static ResponseBean error(){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setState(RESULT_ERROR);
        return responseBean;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}



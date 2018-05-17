package com.iw.tms.exception;

/**
 * Service 异常类
 * @author Lvhoufa
 */
public class ServiceException extends RuntimeException {
    // TODO: 2018/5/1 0001 封装一个service异常类

    public ServiceException(){}

    public ServiceException(Throwable th) {
        super(th);
    }

    public ServiceException(String message,Throwable th) {
        super(message,th);
    }

    public ServiceException(String message) {
        super(message);
    }
}

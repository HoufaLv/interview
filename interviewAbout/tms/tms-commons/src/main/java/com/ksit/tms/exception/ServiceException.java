package com.ksit.tms.exception;

/**
 * 业务逻辑异常
 * @author Lvhoufa
 */
public class ServiceException extends RuntimeException {

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

package com.iw.crm.exception;

public class ServiceException extends RuntimeException {
    public ServiceException(){}

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable th) {
        super(th);
    }

    public ServiceException(Throwable th,String message) {
        super(message,th);
    }
}

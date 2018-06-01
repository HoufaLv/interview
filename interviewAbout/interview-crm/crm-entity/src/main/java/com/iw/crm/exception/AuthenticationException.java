package com.iw.crm.exception;

/**
 * 账号登陆异常
 */
public class AuthenticationException extends RuntimeException {

    public AuthenticationException(){}

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(Throwable th) {
        super(th);
    }

    public AuthenticationException(Throwable th,String message) {
        super(message,th);
    }


}

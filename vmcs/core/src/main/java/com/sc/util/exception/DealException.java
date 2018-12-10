package com.sc.util.exception;

/**
 * what:    操作交易异常
 *
 * @author 孙超 created on 2018/8/8
 */
public class DealException  extends RuntimeException{
    public DealException() {
    }

    public DealException(String message) {
        super(message);
    }

    public DealException(String message, Throwable cause) {
        super(message, cause);
    }

    public DealException(Throwable cause) {
        super(cause);
    }

    public DealException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

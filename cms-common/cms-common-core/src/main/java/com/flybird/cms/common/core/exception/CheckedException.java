package com.flybird.cms.common.core.exception;

/**
 * description: 检查异常
 *
 * @author: flybird
 * @date: 2021-12-28 22:16:27
 */
public class CheckedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CheckedException(String message) {
        super(message);
    }

    public CheckedException(Throwable cause) {
        super(cause);
    }

    public CheckedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

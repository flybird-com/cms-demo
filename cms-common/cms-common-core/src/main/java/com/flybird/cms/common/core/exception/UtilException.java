package com.flybird.cms.common.core.exception;

/**
 * description: 工具类异常
 *
 * @author: flybird
 * @date: 2021-12-28 22:18:41
 */
public class UtilException extends RuntimeException {

    private static final long serialVersionUID = 8247610319171014183L;

    public UtilException(Throwable e) {
        super(e.getMessage(), e);
    }

    public UtilException(String message) {
        super(message);
    }

    public UtilException(String message, Throwable throwable) {
        super(message, throwable);
    }

}

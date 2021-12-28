package com.flybird.cms.common.core.exception;

/**
 * description: 内部认证异常
 *
 * @author: flybird
 * @date: 2021-12-28 22:17:39
 */
public class InnerAuthException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InnerAuthException(String message) {
        super(message);
    }
}

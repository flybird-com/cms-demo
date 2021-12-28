package com.flybird.cms.common.core.exception;

/**
 * description: 验证码错误异常类
 *
 * @author: flybird
 * @date: 2021-12-28 22:16:15
 */
public class CaptchaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CaptchaException(String msg) {
        super(msg);
    }
}

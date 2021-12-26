package com.flybird.cms.common.core.exception;

/**
 * description: 验证码错误异常类
 *
 * @author: flybird
 * @date: 2021-12-26 6:19 下午
 */
public class CaptchaException extends RuntimeException {

    public CaptchaException(String msg) {
        super(msg);
    }
}

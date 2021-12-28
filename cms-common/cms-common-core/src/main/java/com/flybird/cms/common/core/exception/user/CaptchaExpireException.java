package com.flybird.cms.common.core.exception.user;

/**
 * description: 验证码失效异常类
 *
 * @author: flybird
 * @date: 2021-12-28 22:14:57
 */
public class CaptchaExpireException extends UserException {
    private static final long serialVersionUID = 1L;

    public CaptchaExpireException() {
        super("user.captcha.expire", null);
    }
}

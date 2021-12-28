package com.flybird.cms.common.core.exception.auth;

/**
 * description: 未能通过的登录认证异常
 *
 * @author: flybird
 * @date: 2021-12-28 22:15:20
 */
public class NotLoginException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public NotLoginException(String message)
    {
        super(message);
    }
}

package com.flybird.cms.common.core.exception.auth;

import org.apache.commons.lang3.StringUtils;

/**
 * description: 未能通过的权限认证异常
 *
 * @author: flybird
 * @date: 2021-12-28 22:10:17
 */
public class NotPermissionException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public NotPermissionException(String permission)
    {
        super(permission);
    }

    public NotPermissionException(String[] permissions)
    {
        super(StringUtils.join(permissions, ","));
    }
}

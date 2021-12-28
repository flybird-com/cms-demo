package com.flybird.cms.common.core.exception.auth;

import org.apache.commons.lang3.StringUtils;

/**
 * description: 未能通过的角色认证异常
 *
 * @author: flybird
 * @date: 2021-12-28 22:10:38
 */
public class NotRoleException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public NotRoleException(String role)
    {
        super(role);
    }

    public NotRoleException(String[] roles)
    {
        super(StringUtils.join(roles, ","));
    }
}

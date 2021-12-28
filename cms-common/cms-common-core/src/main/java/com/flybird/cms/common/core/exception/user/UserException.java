package com.flybird.cms.common.core.exception.user;


import com.flybird.cms.common.core.exception.base.BaseException;

/**
 * description: 用户信息异常类
 *
 * @author: flybird
 * @date: 2021-12-28 22:15:30
 */
public class UserException extends BaseException {

    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}

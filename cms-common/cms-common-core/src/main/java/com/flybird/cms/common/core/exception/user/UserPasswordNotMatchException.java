package com.flybird.cms.common.core.exception.user;

/**
 * description: 用户密码不正确或不符合规范异常类
 *
 * @author: flybird
 * @date: 2021-12-28 22:15:59
 */
public class UserPasswordNotMatchException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserPasswordNotMatchException() {
        super("user.password.not.match", null);
    }
}

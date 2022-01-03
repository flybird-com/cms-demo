package com.flybird.cms.common.security.annotation;

/**
 * description: 权限注解的验证模式
 *
 * @author: flybird
 * @date: 2022-01-03 11:29:32
 */
public enum Logical {
    /**
     * 必须具有所有的元素
     */
    AND,

    /**
     * 只需具有其中一个元素
     */
    OR
}

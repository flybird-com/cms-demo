package com.flybird.cms.common.security.annotation;

import java.lang.annotation.*;

/**
 * description: 内部认证注解
 *
 * @author: flybird
 * @date: 2022-01-03 11:28:19
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InnerAuth {
    /**
     * 是否校验用户信息
     */
    boolean isUser() default false;
}

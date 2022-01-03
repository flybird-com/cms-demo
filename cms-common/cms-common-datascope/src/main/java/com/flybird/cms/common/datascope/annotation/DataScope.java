package com.flybird.cms.common.datascope.annotation;

import java.lang.annotation.*;

/**
 * description: 数据权限过滤注解
 *
 * @author: flybird
 * @date: 2022-01-01 14:45:49
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {

    /**
     * 部门表的别名
     */
    public String deptAlias() default "";

    /**
     * 用户表的别名
     */
    public String userAlias() default "";
}

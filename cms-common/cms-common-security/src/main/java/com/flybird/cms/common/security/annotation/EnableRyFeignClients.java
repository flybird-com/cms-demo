package com.flybird.cms.common.security.annotation;

import org.springframework.cloud.openfeign.EnableFeignClients;

import java.lang.annotation.*;

/**
 * description: 自定义feign注解 添加basePackages路径
 *
 * @author: flybird
 * @date: 2022-01-03 11:26:39
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableFeignClients
public @interface EnableRyFeignClients {

    String[] value() default {};

    String[] basePackages() default {"com.flybird.cms"};

    Class<?>[] basePackageClasses() default {};

    Class<?>[] defaultConfiguration() default {};

    Class<?>[] clients() default {};

}

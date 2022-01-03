package com.flybird.cms.auth;

import com.flybird.cms.common.security.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * description: 认证授权中心
 *
 * @author: flybird
 * @date: 2022-01-03 17:57:53
 */
@EnableRyFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CmsAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsAuthApplication.class, args);
    }
}

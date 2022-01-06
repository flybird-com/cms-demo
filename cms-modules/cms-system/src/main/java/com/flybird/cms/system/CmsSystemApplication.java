package com.flybird.cms.system;

import com.flybird.cms.common.security.annotation.EnableCustomConfig;
import com.flybird.cms.common.security.annotation.EnableRyFeignClients;
import com.flybird.cms.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * description: 系统模块
 *
 * @author: flybird
 * @date: 2022-01-03 19:34:30
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class CmsSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsSystemApplication.class, args);
    }
    
}

package com.flybird.cms.job;

import com.flybird.cms.common.security.annotation.EnableCustomConfig;
import com.flybird.cms.common.security.annotation.EnableRyFeignClients;
import com.flybird.cms.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * description: 定时任务
 *
 * @author: flybird
 * @date: 2022-01-09 17:50:51
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class CmsJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsJobApplication.class, args);
    }

}

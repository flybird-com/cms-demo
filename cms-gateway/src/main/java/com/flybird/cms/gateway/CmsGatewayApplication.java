package com.flybird.cms.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @description: 网关启动程序
 * @author: flybird
 * @date: 2021/12/23 10:49 下午
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CmsGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsGatewayApplication.class, args);

    }
}

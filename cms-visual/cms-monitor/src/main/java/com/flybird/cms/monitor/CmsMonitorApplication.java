package com.flybird.cms.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * description: 监控中心
 *
 * @author: flybird
 * @date: 2022-01-03 18:15:15
 */
@EnableAdminServer
@SpringBootApplication
public class CmsMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsMonitorApplication.class, args);
    }

}

package com.flybird.cms.file;

import com.flybird.cms.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * description: 文件服务
 *
 * @author: flybird
 * @date: 2022-01-09 18:20:21
 */
@EnableCustomSwagger2
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CmsFileApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsFileApplication.class, args);
    }
}

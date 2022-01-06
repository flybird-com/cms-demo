package com.flybird.cms.gen.utils;

import com.flybird.cms.common.core.constant.Constants;
import org.apache.velocity.app.Velocity;

import java.util.Properties;

/**
 * description: VelocityEngine工厂
 *
 * @author: flybird
 * @date: 2022-01-03 21:03:59
 */
public class VelocityInitializer {

    /**
     * 初始化vm方法
     */
    public static void initVelocity() {
        Properties p = new Properties();
        try {
            // 加载classpath目录下的vm文件
            p.setProperty("resource.loader.file.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            // 定义字符集
            p.setProperty(Velocity.INPUT_ENCODING, Constants.UTF8);
            // 初始化Velocity引擎，指定配置Properties
            Velocity.init(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

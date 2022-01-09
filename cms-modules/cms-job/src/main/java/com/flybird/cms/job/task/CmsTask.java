package com.flybird.cms.job.task;

import com.flybird.cms.common.core.utils.StringUtils;
import org.springframework.stereotype.Component;

/**
 * description: 定时任务调度测试
 *
 * @author: flybird
 * @date: 2022-01-09 17:58:08
 */
@Component("cmsTask")
public class CmsTask {

    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i) {
        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void ryParams(String params) {
        System.out.println("执行有参方法：" + params);
    }

    public void ryNoParams() {
        System.out.println("执行无参方法");
    }
}

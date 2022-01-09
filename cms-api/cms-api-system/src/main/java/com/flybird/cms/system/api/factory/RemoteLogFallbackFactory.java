package com.flybird.cms.system.api.factory;

import com.flybird.cms.common.core.domain.R;
import com.flybird.cms.system.api.RemoteLogService;
import com.flybird.cms.system.api.domain.SysLogininfor;
import com.flybird.cms.system.api.domain.SysOperLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * description: 日志服务降级处理
 *
 * @author: flybird
 * @date: 2022-01-09 18:12:09
 */
@Component
public class RemoteLogFallbackFactory implements FallbackFactory<RemoteLogService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteLogFallbackFactory.class);

    @Override
    public RemoteLogService create(Throwable throwable)
    {
        log.error("日志服务调用失败:{}", throwable.getMessage());
        return new RemoteLogService()
        {
            @Override
            public R<Boolean> saveLog(SysOperLog sysOperLog, String source)
            {
                return null;
            }

            @Override
            public R<Boolean> saveLogininfor(SysLogininfor sysLogininfor, String source)
            {
                return null;
            }
        };

    }
}

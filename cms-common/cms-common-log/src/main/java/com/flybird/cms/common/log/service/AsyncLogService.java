package com.flybird.cms.common.log.service;

import com.flybird.cms.system.api.domain.SysOperLog;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * description: 异步调用日志服务
 *
 * @author: flybird
 * @date: 2021-12-29 21:05:35
 */
@Service
public class AsyncLogService {
//    @Autowired
//    private RemoteLogService remoteLogService;

    /**
     * 保存系统日志记录
     */
    @Async
    public void saveSysLog(SysOperLog sysOperLog) {
//        remoteLogService.saveLog(sysOperLog, SecurityConstants.INNER);
    }
}

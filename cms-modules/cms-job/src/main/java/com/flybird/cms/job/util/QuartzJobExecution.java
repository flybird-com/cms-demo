package com.flybird.cms.job.util;

import com.flybird.cms.job.domain.SysJob;
import org.quartz.JobExecutionContext;

/**
 * description: 定时任务处理（允许并发执行）
 *
 * @author: flybird
 * @date: 2022-01-09 18:00:00
 */
public class QuartzJobExecution extends AbstractQuartzJob {
    @Override
    protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}

package com.flybird.cms.job.util;

import com.flybird.cms.job.domain.SysJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;

/**
 * description: 定时任务处理（禁止并发执行）
 *
 * @author: flybird
 * @date: 2022-01-09 17:59:39
 */
@DisallowConcurrentExecution
public class QuartzDisallowConcurrentExecution extends AbstractQuartzJob {

    @Override
    protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}

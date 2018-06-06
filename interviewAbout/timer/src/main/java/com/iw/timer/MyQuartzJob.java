package com.iw.timer;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 演示Quartz 定时任务框架的使用
 */
public class MyQuartzJob implements Job {
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("执行Quartz定时任务");
    }
}

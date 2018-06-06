package com.iw.timer;

import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;

import static org.junit.Assert.*;

public class MyQuartzJobTest {


    @Test
    public void test() throws SchedulerException, IOException {
        //创建JobBuilder
        JobDetail jobDetail = JobBuilder.newJob(MyQuartzJob.class).build();

        //创建调度者创造者
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule();
        //设置调度者 执行间隔
        simpleScheduleBuilder.withIntervalInSeconds(5);

        //永远执行下去
        simpleScheduleBuilder.repeatForever();

        //创建触发器
        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger().withSchedule(simpleScheduleBuilder).build();

        //创建调度者
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.scheduleJob(jobDetail,simpleTrigger);

        //开始任务调度
        scheduler.start();

        System.in.read();
    }

    @Test
    public void cronTriggerTestCase() throws SchedulerException, IOException {

        JobDetail detail = JobBuilder.newJob(MyQuartzJob.class).build();

        //每五秒
        ScheduleBuilder ssb = CronScheduleBuilder.cronSchedule("0/5 * * * * ? *");

        Trigger trigger = TriggerBuilder.newTrigger().withSchedule(ssb).build();

        //创建调度者
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.scheduleJob(detail,trigger);
        scheduler.start();

        System.in.read();
    }

}
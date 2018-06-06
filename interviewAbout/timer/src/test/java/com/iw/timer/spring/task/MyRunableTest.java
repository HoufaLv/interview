package com.iw.timer.spring.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class MyRunableTest {


    @Autowired
    private ThreadPoolTaskScheduler scheduler;

    @Test
    public void springTaksTestCase() throws IOException {
        MyRunable myRunable = new MyRunable();

        //执行任务
        //scheduler.execute(myRunable);

        //在指定的时间执行任务,就是现在
        //scheduler.schedule(myRunable,new Date());

//        scheduler.schedule(myRunable,new Date(System.currentTimeMillis()+5000));
//        System.in.read();

        //延迟执行,会以多线程的方式运行
        //scheduler.scheduleWithFixedDelay(myRunable,1000L);

        //以表达式运行
        //scheduler.schedule(myRunable,new CronTrigger("0/1 * * * * *"));

        //System.in.read();
    }
}
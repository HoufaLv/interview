package com.iw.timer.spring;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskJob {

    /**
     * 加了@Async之后,即便这个任务需要2000 毫秒才完毕,但是仍然可以开启多个线程去100毫秒做一次
     * 最多开启10个进程
     */
    @Scheduled(fixedRate = 100)
    @Async
    public void rateJob(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("定时任务启动" + Thread.currentThread().getName());
    }
}

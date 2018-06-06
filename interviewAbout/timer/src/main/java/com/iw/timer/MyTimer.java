package com.iw.timer;


import java.util.TimerTask;

/**
 * 使用JDK 自带的Timer 继承 TierTask 类
 */
public class MyTimer extends TimerTask {


    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        System.out.println("你好");
    }
}

package com.iw.timer.spring.task;

public class MyRunable implements Runnable{


    public void run() {
        System.out.println("spring thread pool" + Thread.currentThread().getName());
    }
}

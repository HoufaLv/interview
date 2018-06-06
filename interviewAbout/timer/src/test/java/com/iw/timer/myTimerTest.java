package com.iw.timer;

import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;

public class myTimerTest {

    @Test
    public void run() throws IOException {
        Timer timer = new Timer();

        //延时1000毫秒来执行
        //timer.schedule(new MyTimer(),1000L);

        //立即执行
        timer.schedule(new MyTimer(),new Date());

        System.in.read();
    }
}
package com.iw.timer.spring.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class SendMail {

    /**
     * 如果不使用异步,则必须要等待邮件发送完毕之后才能执行Service 中的方法
     * 也就是说必须等待sendMail方法完毕
     * 使用异步,可以发着邮件
     * @param message
     */
    @Async
    public void sendMail(String message){
        System.out.println("开始发送邮件");
        System.out.println("send Mail " + message);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(1/0);
        System.out.println("发送邮件");
    }
}

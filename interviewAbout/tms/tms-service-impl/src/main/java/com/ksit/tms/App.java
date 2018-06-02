package com.ksit.tms;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring*.xml");

        classPathXmlApplicationContext.start();
        System.out.println("服务启动完毕");
        System.in.read();
    }
}

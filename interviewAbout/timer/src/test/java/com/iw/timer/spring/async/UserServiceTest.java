package com.iw.timer.spring.async;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-async.xml")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void sendMeailAsyncTestCase(){

        userService.reg();

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
package com.iw.timer.spring.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
//引用spring-task-annotation.xml 配置文件
@ContextConfiguration(locations = "classpath:spring-task-annotation.xml")
public class SpringTaskJobTestCase {

    @Test
    public void springTaskTestCase() throws IOException {

//        关于任务调度,比如说30分钟之后,订单取消,这种任务,就做不了,每天晚上查看年票是否过期,就可以做
        System.out.println("开启spring注解 任务调度");
        System.in.read();
    }
}

package com.iw.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iw.service.AccountService;

public class TestCase {
	
	@Test
	public void testAccountService() {
		//注入类和被注入类,都要放入容器中,通过容器获得bean,然后调用bean中的方法,期间容器会负责baen 的实例化和生命周期
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/*.xml");
		AccountService accountService = (AccountService) applicationContext.getBean("accountService");
		
		accountService.insertAccount();
	}
}

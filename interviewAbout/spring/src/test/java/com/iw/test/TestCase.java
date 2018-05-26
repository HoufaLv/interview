package com.iw.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iw.service.AccountService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/*.xml")
public class TestCase {
	
	@Autowired
	private AccountService accountService;
	
	@Test
	public void testAccountService() {
//		//注入类和被注入类,都要放入容器中,通过容器获得bean,然后调用bean中的方法,期间容器会负责baen 的实例化和生命周期
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/*.xml");
//		AccountService accountService = (AccountService) applicationContext.getBean("accountService");
		
		
		accountService.insertAccount();
	}
}

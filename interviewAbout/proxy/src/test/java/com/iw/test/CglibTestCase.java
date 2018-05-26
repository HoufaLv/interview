package com.iw.test;

import org.junit.Test;

import cglibProxy.BookShopCglibProxy;
import cglibProxy.BookShopImpl;

public class CglibTestCase {

	
	@Test
	public void cglibTestCase() {
		 BookShopImpl bookShopImpl = new BookShopImpl();
		 BookShopCglibProxy bookShopCglibProxy = new BookShopCglibProxy();
		 
		BookShopImpl bookShopImp2 = (BookShopImpl) bookShopCglibProxy.getInstance(bookShopImpl);
		bookShopImp2.saleBook();
		
		
	}
}

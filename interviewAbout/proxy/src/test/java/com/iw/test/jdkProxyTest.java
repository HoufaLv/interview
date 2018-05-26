package com.iw.test;

import org.junit.Test;

import jdkProxy.BookStoreProxy;
import jdkProxy.BookStore;
import jdkProxy.BookStoreImpl;

public class jdkProxyTest {
	
	@Test
	public void jdkProxyTestCase() {
		BookStoreImpl bookStoreImpl = new BookStoreImpl();
		BookStoreProxy bookStoreProxy = new BookStoreProxy();
		
		BookStore bookStore = (BookStore) bookStoreProxy.bind(bookStoreImpl);
		
		bookStore.addBook();
	}
}

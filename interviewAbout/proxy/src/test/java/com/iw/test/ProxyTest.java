package com.iw.test;

import org.junit.Test;

import staticProxy.AccountImpl;
import staticProxy.AccountProxy;

public class ProxyTest {

	@Test
	public void testStaticProxy() {
		AccountProxy accountProxy = new AccountProxy(new AccountImpl());
		accountProxy.queryAccount();

		accountProxy.updateAccount();
	}

	public static void main(String[] args) {
		
	}

}

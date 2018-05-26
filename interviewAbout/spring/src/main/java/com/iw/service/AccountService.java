package com.iw.service;

import com.iw.dao.AccountDao;

/**
 * 使用set注入,将accountDao和accountService 都注入到spring 容器中
 * @author Lvhoufa
 *
 */
public class AccountService {
	
	private AccountDao accountDao;
	
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
	
	/**
	 * 添加账户
	 */
	public void insertAccount() {
		accountDao.insert();
	}
}

package com.benqcz.account.test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import com.benqcz.account.dao.impl.AccountDaoImpl;
import com.benqcz.account.domain.Account;
import com.benqcz.account.service.impl.AccountServiceImpl;

public class AlllTest {

	@Test
	public void testAddAccount() {
		Account account = new Account();
		account.setId(1);
		account.setName("ben");
		account.setBalance(400);
		Assert.assertEquals(true, new AccountDaoImpl().addAccount(account));
	}
	
	@Test
	public void testGetAccountById() {
		Assert.assertEquals("ben", new AccountDaoImpl().findAccountById(1).getName());
	}
	
	@Test
	public void testTransfer() {
		new AccountServiceImpl().transfer(1, 2, 200);
	}

}

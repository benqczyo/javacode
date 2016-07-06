package com.benqcz.ikanke.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.benqcz.ikanke.dao.AccountDao;
import com.benqcz.ikanke.dao.impl.AccountDaoImpl;
import com.benqcz.ikanke.factory.DaoFactory;

public class TestAccountDaoImpl {
	
	private AccountDao dao = DaoFactory.getDaoInstance(AccountDaoImpl.class);

	@Test
	public void testAddAccount() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelAccountByName() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateAccount() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllAccounts() {
		System.out.println(dao.findAllAccounts());
	}

	@Test
	public void testFindAccountByName() {
		System.out.println(dao.findAccountByName("orcl"));
	}

	@Test
	public void testFindAccount() {
		fail("Not yet implemented");
	}

}

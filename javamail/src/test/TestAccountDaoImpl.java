package test;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import utils.SHA1Utils;

import dao.AccountDao;
import dao.impl.AccountDaoImpl;
import domain.AccountBean;

public class TestAccountDaoImpl {
	
	private AccountDao dao = new AccountDaoImpl();

	@Test
	public void testAddAccount() {
		AccountBean account = new AccountBean();
		account.setId(UUID.randomUUID().toString());
		account.setName("sue");
		account.setPassword(SHA1Utils.encoding("sorry"));
		account.setEmail("sue@hotmail.com");
		Assert.assertTrue(dao.addAccount(account));
	}

	@Test
	public void testDelAccountById() {
		Assert.assertTrue(dao.delAccountById("ec1712bb-01c5-4a88-8503-85e2f61e5ff0"));
	}

	@Test
	public void testDelAccountByName() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateAccountById() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateAccountByName() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllAccounts() {
		System.out.println(dao.findAllAccounts());
	}

	@Test
	public void testFindAccountById() {
		System.out.println(dao.findAccountById("f8640a25-d72c-4d71-9d4a-c280a4991a09"));
	}

	@Test
	public void testFindAccountByName() {
		System.out.println(dao.findAccountByName("sue"));
	}

	@Test
	public void testFindAccount() {
		System.out.println(dao.findAccount("sue", "3XsdAojw9RGL6Q71wOA6sbE3w/g="));
	}

}

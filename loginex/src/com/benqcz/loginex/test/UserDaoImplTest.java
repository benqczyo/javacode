package com.benqcz.loginex.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.benqcz.loginex.dao.impl.UserDaoImpl;
import com.benqcz.loginex.domain.UserBean;

public class UserDaoImplTest {

	@Test
	public void testAddUser() {
		UserBean user = new UserBean();
		user.setId("1");
		user.setName("benjamin");
		user.setPassword("heelo");
		user.setEmail("benqcz@hotmail.com");
		new UserDaoImpl().addUser(user);
	}

	@Test
	public void testDelUserById() {
		Assert.assertEquals(true, new UserDaoImpl().delUserById("1"));
	}

	@Test
	public void testFindAllUsers() {
		System.out.println(new UserDaoImpl().findAllUsers());
	}

	@Test
	public void testFindUserByName() {
		assertNotNull(new UserDaoImpl().findUserByName("benjamin"));
	}

	@Test
	public void testUpdateUser() {
		UserBean user = new UserBean();
		user.setId("1");
		user.setName("benqcz");
		user.setPassword("9527");
		user.setEmail("benqcz@hotmail.com");
		new UserDaoImpl().updateUser(user);
	}

}

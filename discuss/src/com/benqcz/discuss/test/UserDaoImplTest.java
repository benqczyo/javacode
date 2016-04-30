package com.benqcz.discuss.test;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.benqcz.discuss.dao.impl.UserDaoImpl;
import com.benqcz.discuss.domain.User;

public class UserDaoImplTest extends TestCase {

	public void testAddUser() {
		User user = new User();
		user.setEmail("benqcz@hotmail.com");
		user.setUsername("benjamin");
		user.setPassword("sorry");
		user.setBirthday("2000-10-01");
		Assert.assertEquals(true, new UserDaoImpl().addUser(user));
		user.setEmail("benqczyo@hotmail.com");
		user.setUsername("jack");
		user.setPassword("sorry");
		user.setBirthday("2000-10-01");
		Assert.assertEquals(true, new UserDaoImpl().addUser(user));
	}

	public void testFindUser() {
		User user = new UserDaoImpl().findUser("benjamin", "sorry");
		assertNotNull(user);
		Assert.assertEquals("benjamin", user.getUsername());
		Assert.assertEquals("sorry", user.getPassword());
	}

	public void testFindUserByName() {
		User user = new UserDaoImpl().findUserByName("benjamin");
		assertNotNull(user);
		Assert.assertEquals("benjamin", user.getUsername());
	}

}

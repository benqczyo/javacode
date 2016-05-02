package com.benqcz.fourm.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import junit.framework.Assert;

import org.junit.Test;

import com.benqcz.fourm.dao.impl.UserDaoImpl;
import com.benqcz.fourm.domain.UserBean;

public class UserDaoImplTest {

	@Test
	public void testAddUser() {
		UserBean user = new UserBean();
		user.setUsername("benjamin");
		user.setPassword("123456");
		user.setEmail("benqcz@hotmail.com");
		user.setBirthday("2000-01-01");
		UserBean result = new UserDaoImpl().addUser(user);
		Assert.assertEquals("benjamin", result.getUsername());
	}

	@Test
	public void testFindUser() {
		UserBean result = new UserDaoImpl().findUser("benjamin", "123456");
		assertNotNull(result);
	}

	@Test
	public void testFindUserByName() {
		fail("Not yet implemented");
	}

}

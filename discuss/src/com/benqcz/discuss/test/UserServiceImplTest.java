package com.benqcz.discuss.test;

import com.benqcz.discuss.domain.User;
import com.benqcz.discuss.exception.UserExistException;
import com.benqcz.discuss.service.impl.UserServiceImpl;
import com.benqcz.discuss.util.SHA1Util;

import junit.framework.Assert;
import junit.framework.TestCase;
import junit.framework.Test;

public class UserServiceImplTest extends TestCase {

	public void testLogin() {
		User user1 = new UserServiceImpl().login("benqcz", "9527@linod734");
		assertNotNull(user1);
		Assert.assertEquals("benqcz", user1.getUsername());
		Assert.assertEquals(SHA1Util.sha1("9527@linod734"), user1.getPassword());
		User user2 = new UserServiceImpl().login("benqczm", "9527@linod734");
		assertNull(user2);
	}

	public void testRegister() throws UserExistException {
		User user = new User();
		user.setEmail("benqcz@hotmail.com");
		user.setUsername("benqcz");
		user.setPassword("9527@linod734");
		user.setBirthday("2000-01-01");
		User registedUser = new UserServiceImpl().register(user);
	}

}

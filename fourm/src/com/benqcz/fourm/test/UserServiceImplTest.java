package com.benqcz.fourm.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.benqcz.fourm.domain.UserBean;
import com.benqcz.fourm.exception.UserExistException;
import com.benqcz.fourm.service.impl.UserServiceImpl;

public class UserServiceImplTest {

	@Test
	public void testLogin() {
		UserBean user = new UserServiceImpl().login("benjamin", "123456");
		assertNotNull(user);
	}

	@Test(expected=com.benqcz.fourm.exception.UserExistException.class)
	public void testRegister() throws UserExistException {
		UserBean user = new UserBean();
		user.setUsername("benjamin");
		user.setPassword("123456");
		user.setEmail("benqcz@hotmail.com");
		user.setBirthday("1982-08-01");
		new UserServiceImpl().register(user);
	}

}

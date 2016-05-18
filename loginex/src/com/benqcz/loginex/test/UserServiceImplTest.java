package com.benqcz.loginex.test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import com.benqcz.loginex.domain.UserBean;
import com.benqcz.loginex.service.impl.UserServiceImpl;

public class UserServiceImplTest {

	@Test
	public void testLogin() {
		UserBean userBean = new UserBean();
		userBean.setName("benjamin");
		userBean.setPassword("sorry");
		Assert.assertEquals(true, new UserServiceImpl().login(userBean));
	}

	@Test
	public void testRegister() {
		UserBean userBean = new UserBean();
		userBean.setId("1001");
		userBean.setName("benjamin");
		userBean.setPassword("sorry");
		userBean.setEmail("benqcz@hotmail.com");
		Assert.assertEquals(true, new UserServiceImpl().register(userBean));
	}

}

package com.benqcz.loginex.test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import com.benqcz.loginex.domain.UserBean;
import com.benqcz.loginex.service.impl.UserServiceImpl;

public class UserServiceImplTest {

	@Test
	public void testLogin() {
		
	}

	@Test
	public void testRegister() {
		UserBean userBean = new UserBean();
		userBean.setId("1002");
		userBean.setName("mojo");
		userBean.setPassword("sorry");
		userBean.setEmail("mojo@hotmail.com");
		Assert.assertEquals(true, new UserServiceImpl().register(userBean));
	}

}

package com.benqcz.autologin.test;

import java.util.List;

import com.benqcz.autologin.dao.UserDao;
import com.benqcz.autologin.dao.impl.UserDaoImpl;
import com.benqcz.autologin.domain.UserBean;
import com.benqcz.utils.SHA1Utils;

import junit.framework.Assert;
import junit.framework.TestCase;

public class UserDaoImplTest extends TestCase {
	
	private UserDao dao = new UserDaoImpl();

	public void testAddUser() {
		UserBean user2 = new UserBean();
		user2.setName("张三");
		user2.setPassword(SHA1Utils.encode("yrros"));
		user2.setEmail("lox@163.com");
		dao.addUser(user2);
	}

	public void testDelUserByName() {
		Assert.assertEquals(true, dao.delUserByName("benqcz"));
	}

	public void testFindAllUsers() {
		List<UserBean> users = dao.findAllUsers();
		assertNotNull(users);
		System.out.println(users);
	}

	public void testFindUser() {
		assertNotNull((dao.findUser("lox", SHA1Utils.encode("sorry"))));
	}

	public void testFindUserByName() {
		assertNotNull((dao.findUserByName("lox")));
	}

	public void testUpdateUser() {
		UserBean user = new UserBean();
		user.setName("张三");
		user.setPassword(SHA1Utils.encode("yrros"));
		user.setEmail("lox@163.com");
		Assert.assertEquals(true, dao.updateUser(user));
	}

}

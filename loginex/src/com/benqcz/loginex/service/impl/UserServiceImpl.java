package com.benqcz.loginex.service.impl;

import com.benqcz.loginex.dao.UserDao;
import com.benqcz.loginex.dao.impl.UserDaoImpl;
import com.benqcz.loginex.domain.UserBean;
import com.benqcz.loginex.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDao dao = new UserDaoImpl();

	public boolean login(UserBean user) {
		return dao.findUser(user.getName(), user.getPassword());
	}

	public boolean register(UserBean user) {
		// TODO Auto-generated method stub
		return false;
	}

}

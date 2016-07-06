package com.benqcz.autologin.service.impl;

import com.benqcz.autologin.dao.UserDao;
import com.benqcz.autologin.dao.impl.UserDaoImpl;
import com.benqcz.autologin.domain.UserBean;
import com.benqcz.autologin.service.UserService;
import com.benqcz.utils.SHA1Utils;

public class UserServiceImpl implements UserService {
	
	private UserDao dao = new UserDaoImpl();

	public UserBean login(String name, String password) {
		return dao.findUser(name, SHA1Utils.encode(password));
	}

	public boolean register(UserBean user) {
		UserBean userBean = dao.findUserByName(user.getName());
		if (userBean != null) throw new UserExistedException("用户已经存在");
		user.setPassword(SHA1Utils.encode(user.getPassword()));
		return dao.addUser(user);
	}
	
}

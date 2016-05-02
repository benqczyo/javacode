package com.benqcz.fourm.service.impl;
import com.benqcz.fourm.dao.UserDao;
import com.benqcz.fourm.dao.impl.UserDaoImpl;
import com.benqcz.fourm.domain.UserBean;
import com.benqcz.fourm.service.UserService;


public class UserServiceImpl implements UserService {
	
	private UserDao dao = new UserDaoImpl();

	@Override
	public UserBean login(String username, String password) {
		return dao.findUser(username, password);
	}

	@Override
	public UserBean register(UserBean user) {
		UserBean result = dao.findUserByName(user.getUsername());
		if (result == null) dao.addUser(user);
		return result;
	}

}

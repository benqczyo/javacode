package com.benqcz.discuss.service.impl;

import com.benqcz.discuss.dao.UserDao;
import com.benqcz.discuss.dao.impl.UserDaoImpl;
import com.benqcz.discuss.domain.User;
import com.benqcz.discuss.exception.UserExistException;
import com.benqcz.discuss.service.UserService;
import com.benqcz.discuss.util.SHA1Util;

public class UserServiceImpl implements UserService {
	
	private UserDao dao = new UserDaoImpl();

	@Override
	public User login(String userName, String password) {
		return dao.findUser(userName, SHA1Util.sha1(password));
	}

	@Override
	public User register(User user) throws UserExistException {
		User result = dao.findUserByName(user.getUsername());
		if (result != null) throw new UserExistException();
		user.setPassword(SHA1Util.sha1(user.getPassword()));
		result = dao.addUser(user);
		return result;
	}

}

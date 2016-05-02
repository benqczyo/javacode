package com.benqcz.fourm.service.impl;
import com.benqcz.fourm.dao.UserDao;
import com.benqcz.fourm.dao.impl.UserDaoImpl;
import com.benqcz.fourm.domain.UserBean;
import com.benqcz.fourm.exception.UserExistException;
import com.benqcz.fourm.service.UserService;
import com.benqcz.fourm.utils.SHA1Utils;


public class UserServiceImpl implements UserService {
	
	private UserDao dao = new UserDaoImpl();

	@Override
	public UserBean login(String username, String password) {
		return dao.findUser(username, SHA1Utils.encode(password));
	}

	@Override
	public UserBean register(UserBean user) throws UserExistException {
		UserBean result = dao.findUserByName(user.getUsername());
		if (result != null) throw new UserExistException("用户已存在");
		user.setPassword(SHA1Utils.encode(user.getPassword()));
		result = dao.addUser(user);
		return result;
	}

}

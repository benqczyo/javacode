package service.impl;

import java.util.List;
import java.util.UUID;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import domain.UserBean;
import exception.UserExistException;
import exception.UserNotFoundException;
import service.UserService;
import utils.SHA1Utils;

public class UserServiceImpl implements UserService {
	
	private UserDao dao = new UserDaoImpl();

	@Override
	public List<UserBean> findAllUser() {
		return dao.findAllUsers();
	}

	@Override
	public UserBean login(String name, String password) {
		UserBean result = dao.findUser(name, SHA1Utils.encoding(password));
		if (result == null) throw new UserNotFoundException("没有指定用户");
		return result;
	}

	@Override
	public boolean register(UserBean user) {
		UserBean result = dao.findUserByName(user.getName());
		if (result != null) throw new UserExistException("用户已经存在");
		user.setId(UUID.randomUUID().toString());
		user.setPassword(SHA1Utils.encoding(user.getPassword()));
		return dao.addUser(user);
	}

}

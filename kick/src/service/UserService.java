package service;

import java.util.List;

import domain.UserBean;

public interface UserService {
	boolean register(UserBean user);
	UserBean login(String name, String password);
	List<UserBean> findAllUser();
}

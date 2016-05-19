package com.benqcz.autologin.dao;

import java.util.List;

import com.benqcz.autologin.domain.UserBean;

public interface UserDao {
	boolean addUser(UserBean user);
	boolean delUserByName(String name);
	boolean updateUser(UserBean user);
	UserBean findUserByName(String name);
	UserBean findUser(String name, String password);
	List<UserBean> findAllUsers();
}

package com.benqcz.loginex.dao;

import java.util.List;

import com.benqcz.loginex.domain.UserBean;

public interface UserDao {
	boolean addUser(UserBean user);
	boolean delUserById(String id);
	boolean updateUser(UserBean user);
	UserBean findUserById(String id);
	List<UserBean> findAllUsers();
}

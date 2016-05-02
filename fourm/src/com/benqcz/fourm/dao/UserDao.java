package com.benqcz.fourm.dao;

import com.benqcz.fourm.domain.UserBean;

public interface UserDao {
	public abstract UserBean addUser(UserBean user);
	public abstract UserBean findUserByName(String username);
	public abstract UserBean findUser(String username, String password);
}

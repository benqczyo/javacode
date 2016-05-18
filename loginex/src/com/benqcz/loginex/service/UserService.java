package com.benqcz.loginex.service;

import com.benqcz.loginex.domain.UserBean;

public interface UserService {
	UserBean login(String name, String password);
	boolean register(UserBean user);
}

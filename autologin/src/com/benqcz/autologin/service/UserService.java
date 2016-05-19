package com.benqcz.autologin.service;

import com.benqcz.autologin.domain.UserBean;

public interface UserService {
	UserBean login(String name, String password);
	boolean register(UserBean user);
}

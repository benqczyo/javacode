package dao;

import java.util.List;

import domain.UserBean;

public interface UserDao {
	boolean addUser(UserBean user);
	boolean delUserById(String id);
	boolean updateUser(UserBean user);
	List<UserBean> findAllUsers();
	UserBean findUserById(String id);
	UserBean findUserByName(String name);
	UserBean findUser(String name, String password);
}

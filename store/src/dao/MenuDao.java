package dao;

import java.util.List;

import domain.MenuBean;

public interface MenuDao {
	boolean addMenu(MenuBean menu);
	boolean delMenuById(String id);
	boolean updateMenu(MenuBean menu);
	List<MenuBean> findAllMenus();
	MenuBean findMenuById(String id);
}

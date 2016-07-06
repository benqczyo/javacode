package dao;

import java.util.List;

import domain.MenuBean;
import domain.RoleBean;

public interface MenuDao {
	int getNumberOfMenus();
	boolean addMenu(MenuBean menu);
	boolean delMenuById(String id);
	boolean delMenusByIds(String[] ids);
	boolean updateMenu(MenuBean menu);
	List<MenuBean> findAllMenus();
	MenuBean findMenuById(String id);
	List<MenuBean> findMenusByRange(int startRowId, int endRowId);
}

package dao;

import java.util.List;

import domain.MenuBean;
import domain.RoleBean;

public interface MenuDao {
	boolean addMenu(MenuBean menu);
	boolean delMenuById(String id);
	boolean delMenusByIds(String[] ids);
	List<MenuBean> findAllMenus();
	MenuBean findMenuById(String id);
	List<MenuBean> findMenusByRange(int startRowId, int endRowId);
	int getNumberOfMenus();
	boolean updateMenu(MenuBean menu);
	
}

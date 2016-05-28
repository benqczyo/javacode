package service;

import java.util.List;

import domain.MenuBean;
import domain.Page;
import domain.RoleBean;

public interface BussinessService {
	/**
	 * 返回菜单记录数
	 * @return 菜单记录数
	 */
	int getNumberOfMenus();
	int getNumberOfRoles();
	/**
	 * 添加菜单
	 * @param menu 菜单对象
	 * @return 添加成功true失败false
	 */
	boolean addMenu(MenuBean menu);
	/**
	 * 根据菜单id删除带弹
	 * @param id 菜单id
	 * @return 删除成功true失败false
	 */
	boolean delMenuById(String id);
	boolean delMenusByIds(String[] ids);
	/**
	 * 更新菜单
	 * @param menu 菜单对象
	 * @return 更新成功true失败false
	 */
	boolean updateMenu(MenuBean menu);
	/**
	 * 查找所有菜单
	 * @return 菜单对象List
	 */
	List<MenuBean> findAllMenus();
	/**
	 * 根据id查找菜单
	 * @param id 菜单id
	 * @return 查找成功返回菜单对象失败返回null
	 */
	MenuBean findMenuById(String id);
	
	/**
	 * 根据页id得到分页对象
	 * @param pageId 页id
	 * @return 分页对象
	 */
	Page getPage(Object target, int pageRange, int pageRecords, String currentPageId);
	
	boolean addRole(RoleBean role);
	boolean delRoleById(String id);
}

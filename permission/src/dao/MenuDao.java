package dao;

import java.util.List;

import domain.MenuBean;
import domain.RoleBean;

public interface MenuDao {
	/**
	 * 返回菜单记录数
	 * @return 菜单记录数
	 */
	int getNumberOfMenus();
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
	/**
	 * 一次删除多个菜单项
	 * @param ids 菜单项id数组
	 * @return 删除成功true失败抛出异常
	 */
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
	 * 根据记录编号范围查找记录
	 * @param startRowId 开始的行号
	 * @param endRowId 结束行号
	 * @return 成功返回记录list对象，失败返回null,错误抛出异常
	 */
	List<MenuBean> findMenusByRange(int startRowId, int endRowId);
	
}

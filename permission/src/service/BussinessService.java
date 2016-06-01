package service;

import java.util.List;

import domain.AccountBean;
import domain.MenuBean;
import domain.Page;
import domain.RoleBean;
import formbean.impl.UpdateRoleFormBean;

public interface BussinessService {
	int getNumberOfMenus();
	int getNumberOfRoles();
	int getNumberOfAccounts();
	boolean addMenu(MenuBean menu);
	boolean delMenuById(String id);
	boolean delMenusByIds(String[] ids);
	boolean updateMenu(MenuBean menu);
	List<MenuBean> findAllMenus();
	MenuBean findMenuById(String id);
	Page getPage(Object target, int pageRange, int pageRecords, String currentPageId);
	boolean addRole(RoleBean role);
	boolean delRoleById(String id);
	RoleBean findRoleById(String id);
	boolean updateRole(RoleBean role);
	boolean delRolesByIds(String[] ids);
	boolean assignMenu(String roleId, String[] menuIds);
	boolean delAssignedMenus(String roleId);
	List<AccountBean> findAllAccount();
	boolean addAccount(AccountBean account);
	List<RoleBean> findAllRoles();
	AccountBean findAccountById(String id);
	boolean assignRole(String accountId, String[] roleIds);
	boolean delAssignedRoles(String accountId);
	AccountBean login(String name, String password);
}

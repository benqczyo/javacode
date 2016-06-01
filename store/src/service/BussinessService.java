package service;

import java.util.List;

import domain.AccountBean;
import domain.MenuBean;
import domain.Page;
import domain.RoleBean;
import formbean.impl.UpdateRoleFormBean;

public interface BussinessService {
	boolean addAccount(AccountBean account);
	boolean addMenu(MenuBean menu);
	boolean addRole(RoleBean role);
	boolean assignMenu(String roleId, String[] menuIds);
	boolean assignRole(String accountId, String[] roleIds);
	boolean delAssignedMenus(String roleId);
	boolean delAssignedRoles(String accountId);
	boolean delMenuById(String id);
	boolean delMenusByIds(String[] ids);
	boolean delRoleById(String id);
	boolean delRolesByIds(String[] ids);
	AccountBean findAccountById(String id);
	List<AccountBean> findAllAccount();
	List<MenuBean> findAllMenus();
	List<RoleBean> findAllRoles();
	MenuBean findMenuById(String id);
	RoleBean findRoleById(String id);
	int getNumberOfAccounts();
	int getNumberOfMenus();
	int getNumberOfRoles();
	Page getPage(Object target, int pageRange, int pageRecords, String currentPageId);
	AccountBean login(String name, String password);
	boolean updateMenu(MenuBean menu);
	boolean updateRole(RoleBean role);
}

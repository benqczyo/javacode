package service.impl;

import java.util.List;
import java.util.UUID;

import com.benqcz.utils.SHA1Utils;

import service.BussinessService;

import dao.AccountDao;
import dao.AccountRoleDao;
import dao.MenuDao;
import dao.RoleDao;
import dao.RoleMenuDao;
import dao.impl.AccountDaoImpl;
import dao.impl.AccountRoleDaoImpl;
import dao.impl.MenuDaoImpl;
import dao.impl.RoleDaoImpl;
import dao.impl.RoleMenuDaoImpl;
import domain.AccountBean;
import domain.MenuBean;
import domain.Page;
import domain.RoleBean;
import formbean.impl.UpdateRoleFormBean;

public class BussinessServiceImpl implements BussinessService {
	
	private MenuDao mDao = new MenuDaoImpl();
	private RoleDao rDao = new RoleDaoImpl();
	private RoleMenuDao rmDao = new RoleMenuDaoImpl();
	private AccountDao aDao = new AccountDaoImpl();
	private AccountRoleDao arDao = new AccountRoleDaoImpl();

	@Override
	public boolean addMenu(MenuBean menu) {
		menu.setId(UUID.randomUUID().toString());
		return mDao.addMenu(menu);
	}

	@Override
	public boolean delMenuById(String id) {
		return mDao.delMenuById(id);
	}

	@Override
	public List<MenuBean> findAllMenus() {
		return mDao.findAllMenus();
	}

	@Override
	public MenuBean findMenuById(String id) {
		return mDao.findMenuById(id);
	}

	@Override
	public boolean updateMenu(MenuBean menu) {
		return mDao.updateMenu(menu);
	}

	@Override
	public boolean delMenusByIds(String[] ids) {
		return mDao.delMenusByIds(ids);
	}

	@Override
	public int getNumberOfMenus() {
		return mDao.getNumberOfMenus();
	}
	
	@Override
	public int getNumberOfRoles() {
		return rDao.getNumberOfRoles();
	}
	

	@Override
	public Page getPage(Object target, int pageRange, int pageRecords, String currentPageId) {
		Page result = null;
		if (target instanceof MenuBean) {
			result = new Page(getNumberOfMenus(), pageRange, pageRecords, Integer.parseInt(currentPageId));
			result.setRecords(mDao.findMenusByRange(result.getStartIndex(), result.getEndIndex()));
		}
		if (target instanceof RoleBean) {
			result = new Page(getNumberOfRoles(), pageRange, pageRecords, Integer.parseInt(currentPageId));
			result.setRecords(rDao.findRolesByRange(result.getStartIndex(), result.getEndIndex()));
		}
		if (target instanceof AccountBean) {
			result = new Page(getNumberOfAccounts(), pageRange, pageRecords, Integer.parseInt(currentPageId));
			result.setRecords(aDao.findAccountsByRange(result.getStartIndex(), result.getEndIndex()));
		}
		return result;
	}


	@Override
	public boolean addRole(RoleBean role) {
		role.setId(UUID.randomUUID().toString());
		return rDao.addRole(role);
	}

	@Override
	public boolean delRoleById(String id) {
		return rDao.delRoleById(id);
		
	}

	@Override
	public RoleBean findRoleById(String id) {
		return rDao.findRolesById(id);
	}

	@Override
	public boolean updateRole(RoleBean role) {
		return rDao.updateRole(role);
	}

	@Override
	public boolean delRolesByIds(String[] ids) {
		return rDao.delRolesByIds(ids);
	}

	@Override
	public boolean assignMenu(String roleId, String[] menuIds) {
		boolean result = false;
		if (rmDao.delRelationsByRoleId(roleId) && rmDao.addRelations(roleId, menuIds))
			result = true;
		return result;
	}

	@Override
	public boolean delAssignedMenus(String roleId) {
		return rmDao.delRelationsByRoleId(roleId);
	}

	@Override
	public List<AccountBean> findAllAccount() {
		return aDao.findAllAccounts();
	}

	@Override
	public int getNumberOfAccounts() {
		return aDao.getNumberOfAccounts();
	}

	@Override
	public boolean addAccount(AccountBean account) {
		account.setId(UUID.randomUUID().toString());
		account.setPassword(SHA1Utils.encode(account.getPassword()));
		return aDao.addAccount(account);
	}

	@Override
	public AccountBean findAccountById(String id) {
		return aDao.findAccountById(id);
	}

	@Override
	public List<RoleBean> findAllRoles() {
		return rDao.findAllRoles();
	}

	@Override
	public boolean assignRole(String accountId, String[] roleIds) {
		boolean result = false;
		if (arDao.delRelationsByAccountId(accountId) && arDao.addRelations(accountId, roleIds))
			result = true;
		return result;
	}

	@Override
	public boolean delAssignedRoles(String accountId) {
		return arDao.delRelationsByAccountId(accountId);
	}
	
}

package dao;

import java.util.List;

import domain.RoleBean;

public interface RoleDao {
	int getNumberOfRoles();
	boolean addRole(RoleBean role);
	boolean delRoleById(String id);
	boolean delRolesByIds(String[] ids);
	boolean updateRole(RoleBean role);
	List<RoleBean> findAllRoles();
	RoleBean findRoleById(String id);
	List<RoleBean> findRolesByRange(int startRowId, int endRowId);
}

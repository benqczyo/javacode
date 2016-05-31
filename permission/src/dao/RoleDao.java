package dao;

import java.util.List;

import domain.RoleBean;
import formbean.impl.UpdateRoleFormBean;

public interface RoleDao {
	List<RoleBean> findAllRoles();
	boolean addRole(RoleBean role);
	List findRolesByRange(int startRowId, int endRowId);
	int getNumberOfRoles();
	boolean delRoleById(String id);
	RoleBean findRolesById(String id);
	boolean updateRole(RoleBean role);
	boolean delRolesByIds(String[] ids);
}

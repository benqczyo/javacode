package dao;

import java.util.List;

import domain.RoleBean;
import formbean.impl.UpdateRoleFormBean;

public interface RoleDao {
	boolean addRole(RoleBean role);
	boolean delRoleById(String id);
	boolean delRolesByIds(String[] ids);
	List<RoleBean> findAllRoles();
	RoleBean findRolesById(String id);
	List findRolesByRange(int startRowId, int endRowId);
	int getNumberOfRoles();
	boolean updateRole(RoleBean role);
}

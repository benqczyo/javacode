package dao;

import java.util.List;

import domain.RoleBean;

public interface RoleDao {

	List<RoleBean> findAllRoles();
	boolean addRole(RoleBean role);
	List findRolesByRange(int startRowId, int endRowId);
	int getNumberOfRoles();
	boolean delRoleById(String id);
	
}

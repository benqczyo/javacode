package dao;

public interface RoleMenuDao {
	boolean addRelations(String rId, String[] mIds);
	boolean delRelationsByMenuId(String id);
	boolean delRelationsByRoleId(String id);
}

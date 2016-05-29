package dao;

public interface RoleMenuDao {
	boolean delRelationsByRoleId(String id);
	boolean addRelations(String rId, String[] mIds);
}

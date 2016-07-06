package dao;

public interface AccountRoleDao {
	boolean addRelations(String aId, String[] rIds);
	boolean delRelationsByAccountId(String id);
	boolean delRelationsByRoleId(String id);
}

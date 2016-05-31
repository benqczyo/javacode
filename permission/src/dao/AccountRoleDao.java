package dao;

public interface AccountRoleDao {
	boolean delRelationsByAccountId(String id);
	boolean addRelations(String aId, String[] rIds);
}

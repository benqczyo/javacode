package dao;

import java.sql.SQLException;

public interface CategoryBookDao {
	boolean delRelationshipByCategoryId(String id);
	boolean delRelationshipByCategoryName(String name);
}

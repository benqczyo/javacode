package com.benqcz.ikanke.dao;

public interface CategoryBookDao {
	boolean delRelationshipByCategoryId(String id);
	boolean delRelationshipByCategoryName(String name);
}

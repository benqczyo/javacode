package com.benqcz.ikanke.dao;

import java.util.List;

import com.benqcz.ikanke.domain.impl.CategoryBean;

public interface CategoryDao {
	int getNumberOfCategories();
	boolean addCategory(CategoryBean category);
	boolean delCategoryById(String id);
	boolean delCategoryByName(String name);
	boolean updateCategory(CategoryBean category);
	List<CategoryBean> findAllCategories();
	List<CategoryBean> findCategoriesByRange(int startRecordId, int endRecordId);
	CategoryBean findCategoryById(String id);
	CategoryBean findCategoryByName(String name);
	
}

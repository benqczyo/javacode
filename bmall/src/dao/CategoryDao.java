package dao;

import java.util.List;

import domain.impl.CategoryBean;

public interface CategoryDao {
	boolean addCategory(CategoryBean category);
	boolean delCategoryById(String id);
	boolean delCategoryByName(String name);
	boolean updateCategory(CategoryBean category);
	List<CategoryBean> findAllCategory();
	CategoryBean findCategoryById(String id);
	CategoryBean findCategoryByName(String name);
}

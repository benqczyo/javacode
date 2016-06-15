package service;

import java.util.List;

import domain.Bean;
import domain.Page;
import domain.impl.CategoryBean;

public interface Service {
	boolean addCategory(CategoryBean category);
	void delCategoryById(String id);
	void delCategoryByName(String name);
	boolean updateCategory(CategoryBean category);
	List<CategoryBean> findAllCategory();
	CategoryBean findCategoryById(String id);
	CategoryBean findCategoryByName(String name);
	Page getPage(Bean bean, String recordsOfSinglePage, String buttonsOfSinglePage, String pageId);
}

package service;

import java.util.List;

import utils.Page;

import domain.Bean;
import domain.impl.CategoryBean;

public interface Service {
	boolean addCategory(CategoryBean category);
	boolean delCategoryById(String id);
	boolean delCategoryByName(String name);
	boolean updateCategory(CategoryBean category);
	List<CategoryBean> findAllCategory();
	CategoryBean findCategoryById(String id);
	CategoryBean findCategoryByName(String name);
	Page getPage(Bean bean, String recordsOfSinglePage, String buttonsOfSinglePage, String pageId);
}

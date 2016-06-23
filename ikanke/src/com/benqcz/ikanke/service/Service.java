package com.benqcz.ikanke.service;

import java.sql.SQLException;
import java.util.List;

import com.benqcz.ikanke.domain.Bean;
import com.benqcz.ikanke.domain.Page;
import com.benqcz.ikanke.domain.impl.BookBean;
import com.benqcz.ikanke.domain.impl.CategoryBean;

public interface Service {
	boolean addCategory(CategoryBean category);
	void delCategoryById(String id);
	void delCategoryByName(String name);
	boolean updateCategory(CategoryBean category);
	List<CategoryBean> findAllCategories();
	CategoryBean findCategoryById(String id);
	CategoryBean findCategoryByName(String name);
	boolean addBook(BookBean book);
	void delBookById(String id);
	List<BookBean> findAllBooks();
	BookBean findBookById(String id);
	Page getPage(String currentPageId, String recordsOfSinglePage, 
			String buttonsOfSinglePage, Bean target);
	Page getPageByCategoryId(String currentPageId,
			String recordsOfSinglePage, String buttonsOfSinglePage, String categoryId);
}

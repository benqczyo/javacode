package com.benqcz.ikanke.service.impl;

import java.util.List;

import com.benqcz.ikanke.dao.impl.BookDaoImpl;
import com.benqcz.ikanke.dao.impl.CategoryDaoImpl;
import com.benqcz.ikanke.domain.Page;
import com.benqcz.ikanke.domain.impl.BookBean;
import com.benqcz.ikanke.domain.impl.CategoryBean;
import com.benqcz.ikanke.factory.DaoFactory;
import com.benqcz.ikanke.service.Service;

public class ServiceImpl implements Service {

	@Override
	public boolean addCategory(CategoryBean category) {
		return DaoFactory.getDaoInstance(CategoryDaoImpl.class).addCategory(
				category);
	}

	@Override
	public void delCategoryById(String id) {
	}

	@Override
	public void delCategoryByName(String name) {
		throw new AbstractMethodError();
	}

	@Override
	public boolean updateCategory(CategoryBean category) {
		return DaoFactory.getDaoInstance(CategoryDaoImpl.class).updateCategory(
				category);
	}

	@Override
	public List<CategoryBean> findAllCategory() {
		return DaoFactory.getDaoInstance(CategoryDaoImpl.class)
				.findAllCategories();
	}

	@Override
	public CategoryBean findCategoryById(String id) {
		return DaoFactory.getDaoInstance(CategoryDaoImpl.class)
				.findCategoryById(id);
	}

	@Override
	public CategoryBean findCategoryByName(String name) {
		return DaoFactory.getDaoInstance(CategoryDaoImpl.class)
				.findCategoryByName(name);
	}

	@Override
	public boolean addBook(BookBean book) {
		return DaoFactory.getDaoInstance(BookDaoImpl.class).addBook(book);
	}

	@Override
	public Page getPage(Page page) {
		// TODO Auto-generated method stub
		return null;
	}

}

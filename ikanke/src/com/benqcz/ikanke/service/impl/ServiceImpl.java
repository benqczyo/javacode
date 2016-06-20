package com.benqcz.ikanke.service.impl;

import java.util.List;

import com.benqcz.ikanke.dao.AbstractDao;
import com.benqcz.ikanke.dao.BookDao;
import com.benqcz.ikanke.dao.CategoryDao;
import com.benqcz.ikanke.dao.impl.BookDaoImpl;
import com.benqcz.ikanke.dao.impl.CategoryBookDaoImpl;
import com.benqcz.ikanke.dao.impl.CategoryDaoImpl;
import com.benqcz.ikanke.domain.Bean;
import com.benqcz.ikanke.domain.Page;
import com.benqcz.ikanke.domain.impl.BookBean;
import com.benqcz.ikanke.domain.impl.CategoryBean;
import com.benqcz.ikanke.factory.DaoFactory;
import com.benqcz.ikanke.service.Service;
import com.benqcz.ikanke.utils.IdUtils;

public class ServiceImpl implements Service {

	private int checkPageParams(String param, int value) {
		return (param != null && !param.trim().isEmpty() && param
				.matches("^[1-9][0-9]*$")) ? Integer.parseInt(param) : value;
	}

	@Override
	public boolean addCategory(CategoryBean category) {
		category.setId(IdUtils.generateId());
		return DaoFactory.getDaoInstance(CategoryDaoImpl.class).addCategory(
				category);
	}

	@Override
	public void delCategoryById(String id) {
		DaoFactory.getDaoInstance(CategoryBookDaoImpl.class).delRelationshipByCategoryId(id);
		DaoFactory.getDaoInstance(CategoryDaoImpl.class).delCategoryById(id);
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
		book.setId(IdUtils.generateId());
		return DaoFactory.getDaoInstance(BookDaoImpl.class).addBook(book);
	}

	@Override
	public Page getPage(String currentPageId, String recordsOfSinglePage, String buttonsOfSinglePage, Bean target) {
		Page result = null;
		int pageId = checkPageParams(currentPageId, 1);
		int records = checkPageParams(recordsOfSinglePage, 10);
		int buttons = checkPageParams(buttonsOfSinglePage, 4);
		if (target instanceof CategoryBean) {
			CategoryDao dao = DaoFactory.getDaoInstance(CategoryDaoImpl.class);
			result = new Page(dao.getNumberOfCategories(), records, buttons, pageId);
			result.setPageRecords(dao.findCategoriesByRange(result.getStartRecordId(), result.getEndRecordId()));
		}
		if (target instanceof BookBean) {
			BookDao bDao = DaoFactory.getDaoInstance(BookDaoImpl.class);
			result = new Page(bDao.getNumberOfBooks(), records, buttons, pageId);
			List<BookBean> books = bDao.findBooksByRange(result.getStartRecordId(), result.getEndRecordId());
			CategoryDao cDao = DaoFactory.getDaoInstance(CategoryDaoImpl.class);
			for (BookBean book : books)
				book.setCategory(cDao.findCategoryById(book.getCategoryId()));
			result.setPageRecords(books);
		}
		return result;
	}

	@Override
	public void delBookById(String id) {
		// ¹ØÁªÉ¾³ý
		DaoFactory.getDaoInstance(BookDaoImpl.class).delBookById(id);
	}

	@Override
	public BookBean findBookById(String id) {
		return DaoFactory.getDaoInstance(BookDaoImpl.class).findBookById(id);
	}
}

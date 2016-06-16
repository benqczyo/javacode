package service.impl;

import java.util.List;
import java.util.UUID;

import dao.BookDao;
import dao.CategoryBookDao;
import dao.CategoryDao;
import dao.impl.BookDaoImpl;
import dao.impl.CategoryBookDaoImpl;
import dao.impl.CategoryDaoImpl;
import domain.Bean;
import domain.Page;
import domain.impl.BookBean;
import domain.impl.CategoryBean;
import exception.AddBookException;
import exception.AddCategoryException;
import exception.DeleteCategoryException;
import exception.UpdateCategoryException;
import service.Service;
import utils.DBCPUtils;
import utils.IdUtils;

public class ServiceImpl implements Service {
	
	private CategoryDao cDao = new CategoryDaoImpl();
	private BookDao bDao = new BookDaoImpl();
	private CategoryBookDao cbDao = new CategoryBookDaoImpl();
	
	private String getSafeNumber(String number) {
		return (number == null || number.trim().isEmpty() || !number.matches("^[1-9][0-9]*$")) ? "1" : number;
	}

	@Override
	public boolean addCategory(CategoryBean category) {
		category.setId(IdUtils.generateId());
		try {
			return cDao.addCategory(category);
		} catch (Exception e) {
			throw new AddCategoryException(e);
		}
	}

	@Override
	public void delCategoryById(String id) {
		DBCPUtils.startTransaction();
		try {
			cbDao.delRelationshipByCategoryId(id);
			cDao.delCategoryById(id);
		} catch (Exception e) {
			DBCPUtils.rollback();
			throw new DeleteCategoryException(e);
		} finally {
			DBCPUtils.commit();
			DBCPUtils.close();
		}
	}

	@Override
	public void delCategoryByName(String name) {
		DBCPUtils.startTransaction();
		try {
			cbDao.delRelationshipByCategoryName(name);
			cDao.delCategoryByName(name);
		} catch (Exception e) {
			DBCPUtils.rollback();
		} finally {
			DBCPUtils.commit();
			DBCPUtils.close();
		}
	}

	@Override
	public boolean updateCategory(CategoryBean category) {
		try {
			return cDao.updateCategory(category);
		} catch (Exception e) {
			throw new UpdateCategoryException(e);
		}
	}

	@Override
	public List<CategoryBean> findAllCategory() {
		return cDao.findAllCategories();
	}

	@Override
	public CategoryBean findCategoryById(String id) {
		return cDao.findCategoryById(id);
	}

	@Override
	public CategoryBean findCategoryByName(String name) {
		return cDao.findCategoryByName(name);
	}

	@Override
	public Page getPage(Bean bean, String recordsOfSinglePage, String buttonsOfSinglePage, String pageId) {
		Page result = null;
		recordsOfSinglePage = getSafeNumber(recordsOfSinglePage);
		buttonsOfSinglePage = getSafeNumber(buttonsOfSinglePage);
		pageId = getSafeNumber(pageId);
		if (bean instanceof CategoryBean) {
			result = new Page(cDao.getNumberOfCategories(), 
					Integer.parseInt(recordsOfSinglePage), Integer.parseInt(buttonsOfSinglePage), Integer.parseInt(pageId));
			result.setPageRecords(cDao.findCategoriesByRange(result.getStartRecordId(), result.getEndRecordId()));
		}
		if (bean instanceof BookBean) {
			result = new Page(bDao.getNumberOfBooks(), 
					Integer.parseInt(recordsOfSinglePage), Integer.parseInt(buttonsOfSinglePage), Integer.parseInt(pageId));
			List<BookBean> books = bDao.findBooksByRange(result.getStartRecordId(), result.getEndRecordId());
			for (BookBean book : books)
				book.setCategory(cDao.findCategoryById(book.getCategoryId()));
			result.setPageRecords(books);
		}
		System.out.println(result);
		return result;
	}

	@Override
	public boolean addBook(BookBean book) {
		try {
			book.setId(IdUtils.generateId());
			return bDao.addBook(book);
		} catch (Exception e) {
			throw new AddBookException(e);
		}
	}
	
}

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
import domain.impl.CategoryBean;
import exception.CategoryExistsException;
import exception.DeleteCategoryException;
import service.Service;
import utils.DBCPUtils;
import utils.IdUtils;
import utils.Page;

public class ServiceImpl implements Service {
	
	private CategoryDao cDao = new CategoryDaoImpl();
	private BookDao bDao = new BookDaoImpl();
	private CategoryBookDao cbDao = new CategoryBookDaoImpl();
	
	private String getSafeNumber(String number) {
		return (number == null || number.trim().isEmpty() || number.matches("^[1-9][0-9]+$")) ? "1" : number;
	}

	@Override
	public boolean addCategory(CategoryBean category) {
		category.setId(IdUtils.generateId());
		try {
			return cDao.addCategory(category);
		} catch (Exception e) {
			throw new CategoryExistsException(e);
		}
	}

	@Override
	public boolean delCategoryById(String id) {
		DBCPUtils.startTransaction();
		try {
			cbDao.delRelationshipByCategoryId(id);
			cDao.delCategoryById(id);
		} catch (Exception e) {
			e.printStackTrace();
			DBCPUtils.rollback();
			throw new DeleteCategoryException(e);
		} finally {
			DBCPUtils.commit();
			DBCPUtils.close();
		}
		return false;
	}

	@Override
	public boolean delCategoryByName(String name) {
		/*DBCPUtils.startTransaction();
		try {
			cbDao.delRelationshipByCategoryName(name);
			cDao.delCategoryByName(name);
		} catch (Exception e) {
			e.printStackTrace();
			DBCPUtils.rollback();
		} finally {
			DBCPUtils.commit();
			DBCPUtils.close();
		}*/
		return false;
	}

	@Override
	public boolean updateCategory(CategoryBean category) {
		return cDao.updateCategory(category);
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
			result.setPageRecords(cDao.findAllCategories());
		}
		return result;
	}
	
}

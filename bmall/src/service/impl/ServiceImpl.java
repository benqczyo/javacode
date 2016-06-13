package service.impl;

import java.util.List;
import java.util.UUID;

import dao.CategoryDao;
import dao.impl.CategoryDaoImpl;
import domain.impl.CategoryBean;
import service.Service;
import utils.DBCPUtils;
import utils.IdUtils;

public class ServiceImpl implements Service {
	
	private CategoryDao cDao = new CategoryDaoImpl();
	private BookDao bDao = new BookDaoImpl();
	private CategoryBookDao cbDao = new CategoryBookDaoImpl();

	@Override
	public boolean addCategory(CategoryBean category) {
		category.setId(IdUtils.generateId());
		return cDao.addCategory(category);
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
		} finally {
			DBCPUtils.commit();
			DBCPUtils.close();
		}
	}

	@Override
	public boolean delCategoryByName(String name) {
		DBCPUtils.startTransaction();
		try {
			cbDao.delRelationshipByCategoryName(name);
			cDao.delCategoryByName(name);
		} catch (Exception e) {
			e.printStackTrace();
			DBCPUtils.rollback();
		} finally {
			DBCPUtils.commit();
			DBCPUtils.close();
		}
	}

	@Override
	public boolean updateCategory(CategoryBean category) {
		return cDao.updateCategory(category);
	}

	@Override
	public List<CategoryBean> findAllCategory() {
		return cDao.findAllCategory();
	}

	@Override
	public CategoryBean findCategoryById(String id) {
		return cDao.findCategoryById(id);
	}

	@Override
	public CategoryBean findCategoryByName(String name) {
		return cDao.findCategoryByName(name);
	}

}

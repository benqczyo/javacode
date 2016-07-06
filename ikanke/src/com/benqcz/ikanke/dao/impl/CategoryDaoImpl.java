package com.benqcz.ikanke.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.benqcz.ikanke.dao.AbstractDao;
import com.benqcz.ikanke.dao.CategoryDao;
import com.benqcz.ikanke.domain.impl.CategoryBean;
import com.benqcz.ikanke.exception.DaoException;
import com.benqcz.ikanke.utils.DBCPUtils;

public class CategoryDaoImpl extends AbstractDao implements CategoryDao {

	private static final String GET_NUMBER_OF_CATEGORIES = "SELECT count(*) FROM category";
	private static final String ADD_CATEGORY = "INSERT INTO category (id, name, description) VALUES (?, ?, ?)";
	private static final String DEL_CATEGORY_BY_ID = "DELETE FROM category WHERE id = ?";
	private static final String DEL_CATEGORY_BY_NAME = "DELETE FROM category WHERE name = ?";
	private static final String UPDATE_CATEGORY = "UPDATE category SET name = ?, description = ? WHERE id = ?";
	private static final String FIND_ALL_CATEGORIES = "SELECT id, name, description FROM category";
	private static final String FIND_CATEGORIES_BY_RANGE = "SELECT id, name, description, row_id FROM (SELECT c.*, ROWNUM as row_id FROM (SELECT id, name, description FROM category) c) WHERE row_id BETWEEN ? AND ?";
	private static final String FIND_CATEGORY_BY_ID = "SELECT id, name, description FROM category WHERE id = ?";
	private static final String FIND_CATEGORY_BY_NAME = "SELECT id, name, description FROM category WHERE name = ?";

	private QueryRunner qr = new QueryRunner();

	@Override
	public int getNumberOfCategories() {
		try {
			return ((Number) qr.query(DBCPUtils.open(),
					GET_NUMBER_OF_CATEGORIES, new ScalarHandler(1))).intValue();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public boolean addCategory(CategoryBean category) {
		try {
			return qr.update(DBCPUtils.open(), ADD_CATEGORY, category.getId(),
					category.getName(), category.getDescription()) == 1;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public boolean delCategoryById(String id) {
		try {
			return qr.update(DBCPUtils.open(), DEL_CATEGORY_BY_ID, id) == 1;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public boolean delCategoryByName(String name) {
		try {
			return qr.update(DBCPUtils.open(), DEL_CATEGORY_BY_NAME, name) == 1;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public boolean updateCategory(CategoryBean category) {
		try {
			return qr.update(DBCPUtils.open(), UPDATE_CATEGORY,
					category.getName(), category.getDescription(),
					category.getId()) == 1;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<CategoryBean> findAllCategories() {
		try {
			return qr.query(DBCPUtils.open(), FIND_ALL_CATEGORIES,
					new BeanListHandler<CategoryBean>(CategoryBean.class));
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<CategoryBean> findCategoriesByRange(int startRecordId,
			int endRecordId) {
		try {
			return qr.query(DBCPUtils.open(), FIND_CATEGORIES_BY_RANGE,
					new BeanListHandler<CategoryBean>(CategoryBean.class),
					startRecordId, endRecordId);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public CategoryBean findCategoryById(String id) {
		try {
			return qr.query(DBCPUtils.open(), FIND_CATEGORY_BY_ID,
					new BeanHandler<CategoryBean>(CategoryBean.class), id);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public CategoryBean findCategoryByName(String name) {
		try {
			return qr.query(DBCPUtils.open(), FIND_CATEGORY_BY_NAME,
					new BeanHandler<CategoryBean>(CategoryBean.class), name);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

}

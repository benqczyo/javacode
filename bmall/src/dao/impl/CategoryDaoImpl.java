package dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.jboss.weld.logging.Category;

import utils.DBCPUtils;
import domain.impl.CategoryBean;
import exception.DaoException;

public class CategoryDaoImpl implements dao.CategoryDao {

	private static final String ADD_CATEGORY = "INSERT INTO category (id, name, description) VALUES (?, ?, ?)";
	private static final String DEL_CATEGORY_ID = "DELETE FROM category WHERE id = ?";
	private static final String DEL_CATEGORY_BY_NAME = "DELETE FROM category WHERE name = ?";
	private static final String UPDATE_CATEGORY = "UPDATE category name = ?, description = ? WHERE id = ?";
	private static final String FIND_ALL_CATEGORY = "SELECT id, name, description FROM category";
	private static final String FIND_CATEGORY_BY_ID = "SELECT id, name, description FROM category WHERE id = ?";
	private static final String FIND_CATEGORY_BY_NAME = "SELECT id, name, description FROM category WHERE name = ?";

	private QueryRunner qr = new QueryRunner();

	@Override
	public boolean addCategory(CategoryBean category) {
		boolean result = false;
		try {
			result = qr.update(DBCPUtils.open(), ADD_CATEGORY,
					category.getId(), category.getName(),
					category.getDescription()) == 1;
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			DBCPUtils.close();
		}
		return result;
	}

	@Override
	public boolean delCategoryById(String id) {
		try {
			return qr.update(DBCPUtils.open(), DEL_CATEGORY_ID, id) == 1;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBCPUtils.close();
		}
	}

	@Override
	public boolean delCategoryByName(String name) {
		try {
			return qr.update(DBCPUtils.open(), DEL_CATEGORY_BY_NAME, name) == 1;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBCPUtils.close();
		}
	}

	@Override
	public boolean updateCategory(CategoryBean category) {
		boolean result = false;
		try {
			result = qr.update(DBCPUtils.open(), UPDATE_CATEGORY,
					category.getName(), category.getDescription(),
					category.getId()) == 1;
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			DBCPUtils.close();
		}
		return result;
	}

	@Override
	public List<CategoryBean> findAllCategory() {
		List<CategoryBean> result = null;
		try {
			result = qr.query(DBCPUtils.open(), FIND_ALL_CATEGORY,
					new BeanListHandler<CategoryBean>(CategoryBean.class));
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBCPUtils.close();
		}
		return result;
	}

	@Override
	public CategoryBean findCategoryById(String id) {
		CategoryBean result = null;
		try {
			result = qr.query(DBCPUtils.open(), FIND_CATEGORY_BY_ID,
					new BeanHandler<CategoryBean>(CategoryBean.class), id);
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBCPUtils.close();
		}
		return result;
	}

	@Override
	public CategoryBean findCategoryByName(String name) {
		CategoryBean result = null;
		try {
			result = qr.query(DBCPUtils.open(), FIND_CATEGORY_BY_NAME,
					new BeanHandler<CategoryBean>(CategoryBean.class), name);
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBCPUtils.close();
		}
		return result;
	}

}

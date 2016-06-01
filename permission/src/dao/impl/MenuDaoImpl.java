package dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.benqcz.utils.jdbc.C3P0Utils;
import com.sun.faces.config.beans.ManagedBeanBean;

import dao.MenuDao;
import domain.MenuBean;
import exception.DaoException;

public class MenuDaoImpl implements MenuDao {

	private final String ADD_MENU = "INSERT INTO menu (id, title, uri, description) VALUES (?, ?, ?, ?)";
	private final String DEL_MENU_BY_ID = "DELETE FROM menu WHERE id = ?";
	private final String FIND_ALL_MENUS = "SELECT id, title, uri, description FROM menu";
	private final String FIND_MENU_BY_ID = "SELECT id, title, uri, description FROM menu WHERE id = ?";
	private final String UPDATE_MENU = "UPDATE menu SET title = ?, uri = ?, description = ? WHERE id = ?";
	private final String FIND_MENU_BY_RANGE = "SELECT id, title, uri, description, row_id FROM (SELECT m.*, ROWNUM as row_id FROM (SELECT id, title, uri, description FROM menu) m) WHERE row_id BETWEEN ? AND ?";
	private final String GET_NUMBER_OF_MENUS = "SELECT count(*) FROM menu";
	
	private QueryRunner qr = new QueryRunner();

	@Override
	public boolean addMenu(MenuBean menu) {
		boolean result = false;
		List<Object> params = new LinkedList<Object>();
		Field[] fields = menu.getClass().getDeclaredFields();
		try {
			for (Field field : fields) {
				params.add(BeanUtils.getProperty(menu, field.getName()));
			}
			result = qr.update(C3P0Utils.open(), ADD_MENU, params.toArray(new Object[0])) == 1;
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			C3P0Utils.close();
		}
		return result;
	}

	@Override
	public boolean delMenuById(String id) {
		try {
			return qr.update(C3P0Utils.open(), DEL_MENU_BY_ID, id) == 1;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			C3P0Utils.close();
		}
	}

	@Override
	public boolean delMenusByIds(String[] ids) {
		boolean result = false;
		List<Object[]> params = new LinkedList<Object[]>();
		for (int i = 0; i < ids.length; i++) {
			params.add(new Object[] {ids[i]});
		}
		try {
			int[] status = qr.batch(C3P0Utils.open(), DEL_MENU_BY_ID, params.toArray(new Object[0][0]));
			for (int i = 0; i < status.length; i++) {
				if (status[i] != 1) result = false;
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			C3P0Utils.close();
		}
		return result;
	}

	@Override
	public List<MenuBean> findAllMenus() {
		try {
			return qr.query(C3P0Utils.open(), FIND_ALL_MENUS, new BeanListHandler<MenuBean>(MenuBean.class));
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			C3P0Utils.close();
		}
	}

	@Override
	public MenuBean findMenuById(String id) {
		try {
			return qr.query(C3P0Utils.open(), FIND_MENU_BY_ID, new BeanHandler<MenuBean>(
					MenuBean.class), new Object[] { id });
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			C3P0Utils.close();
		}
	}

	@Override
	public List<MenuBean> findMenusByRange(int startRowId, int endRowId) {
		try {
			return qr.query(C3P0Utils.open(), FIND_MENU_BY_RANGE, new BeanListHandler<MenuBean>(MenuBean.class), new Object[] {startRowId, endRowId});
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			C3P0Utils.close();
		}
	}

	@Override
	public int getNumberOfMenus() {
		try {
			return ((Number) qr.query(C3P0Utils.open(), GET_NUMBER_OF_MENUS, new ScalarHandler(1))).intValue();
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			C3P0Utils.close();
		}
	}

	@Override
	public boolean updateMenu(MenuBean menu) {
		List<Object> params = null;
		try {
			for (Field field : menu.getClass().getDeclaredFields()) {
				if (params == null)
					params = new ArrayList<Object>();
				params.add(BeanUtils.getProperty(menu, field.getName()));
			}
			params.add(params.remove(0));
			return qr.update(C3P0Utils.open(), UPDATE_MENU, params.toArray(new Object[0])) == 1;
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			C3P0Utils.close();
		}
	}

}

package dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.benqcz.utils.jdbc.C3P0Utils;

import dao.MenuDao;
import domain.MenuBean;
import exception.MenuDaoException;

public class MenuDaoImpl implements MenuDao {

	private final String ADD_MENU = "INSERT INTO menu (id, title, uri, description) VALUES (?, ?, ?, ?)";
	private final String DEL_MENU_BY_ID = "DELETE FROM menu WHERE id = ?";
	private final String FIND_ALL_MENUS = "SELECT id, title, uri, description FROM menu";
	private final String FIND_MENU_BY_ID = "SELECT id, title, uri, description FROM menu WHERE id = ?";
	private final String UPDATE_MENU = "UPDATE menu SET title = ?, uri = ?, description = ? WHERE id = ?";

	// 使用c3p0数据源，直接从池中拿到链接用后可自动关闭
	private QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());

	@Override
	public boolean addMenu(MenuBean menu) {
		boolean result = false;
		List<Object> params = new LinkedList<Object>();
		Field[] fields = menu.getClass().getDeclaredFields();
		try {
			for (Field field : fields) {
				params.add(BeanUtils.getProperty(menu, field.getName()));
			}
			result = qr.update(ADD_MENU, params.toArray(new Object[0])) == 1;
		} catch (Exception e) {
			throw new MenuDaoException(e);
		}
		return result;
	}

	@Override
	public boolean delMenuById(String id) {
		try {
			return qr.update(DEL_MENU_BY_ID, id) == 1;
		} catch (SQLException e) {
			throw new MenuDaoException(e);
		}
	}

	@Override
	public List<MenuBean> findAllMenus() {
		try {
			return qr.query(FIND_ALL_MENUS, new BeanListHandler<MenuBean>(
					MenuBean.class));
		} catch (SQLException e) {
			throw new MenuDaoException(e);
		}
	}

	@Override
	public MenuBean findMenuById(String id) {
		try {
			return qr.query(FIND_MENU_BY_ID, new BeanHandler<MenuBean>(
					MenuBean.class), new Object[] { id });
		} catch (SQLException e) {
			throw new MenuDaoException(e);
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
			return qr.update(UPDATE_MENU, params.toArray(new Object[0])) == 1;
		} catch (Exception e) {
			throw new MenuDaoException(e);
		}

	}

}

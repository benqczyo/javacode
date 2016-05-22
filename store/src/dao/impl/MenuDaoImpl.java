package dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;

import com.benqcz.dbutils.C3P0Utils;

import dao.MenuDao;
import domain.MenuBean;
import exception.MenuDaoException;

public class MenuDaoImpl implements MenuDao {
	
	private final String ADD_MENU = "INSERT INTO menu (id, title, uri, description) VALUES (?, ?, ?, ?)";

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
			e.printStackTrace();
			throw new MenuDaoException(e);
		}
		return result;
	}

	@Override
	public boolean delMenuById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<MenuBean> findAllMenus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MenuBean findMenuById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateMenu(MenuBean menu) {
		// TODO Auto-generated method stub
		return false;
	}

}

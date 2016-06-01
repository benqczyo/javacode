package dao.impl;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.benqcz.utils.jdbc.C3P0Utils;

import dao.RoleDao;
import domain.MenuBean;
import domain.RoleBean;
import exception.DaoException;

public class RoleDaoImpl implements RoleDao {
	
	private final String GET_NUMBER_OF_ROLES = "SELECT count(*) FROM role";
	private final String ADD_ROLE = "INSERT INTO role (id, name, description) VALUES (?, ?, ?)";
	private final String DEL_ROLE_BY_ID = "DELETE FROM role WHERE id = ?";
	private final String UPDATE_ROLE = "UPDATE role SET name = ?, description = ? WHERE id = ?";
	private final String FIND_ALL_ROLES = "SELECT id, name, description FROM role";
	private final String FIND_MENUS_BY_ROLE_ID = "SELECT id, title, uri, description FROM menu WHERE id IN (SELECT m_id FROM role_menu WHERE r_id = ?)";
	private final String FIND_ROLE_BY_ID = "SELECT id, name, description FROM role WHERE id = ?";
	private final String FIND_ROLE_BY_RANGE = "SELECT id, name, description, row_id FROM (SELECT r.*, ROWNUM as row_id FROM (SELECT id, name, description FROM role) r) WHERE row_id BETWEEN ? AND ?";

	private QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
	
	public int getNumberOfRoles() {
		int result = -1;
		try {
			result = ((Number) qr.query(GET_NUMBER_OF_ROLES, new ScalarHandler(1))).intValue();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return result;
	}
	
	public boolean addRole(RoleBean role) {
		boolean result = false;
		List<Object> params = new LinkedList<Object>();
		Field[] fields = role.getClass().getDeclaredFields();
		try {
			for (Field field : fields) {
				String fieldName = field.getName();
				if (!fieldName.equalsIgnoreCase("menus")) params.add(BeanUtils.getProperty(role, field.getName()));
			}
			result = qr.update(ADD_ROLE, params.toArray(new Object[0])) == 1;
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return result;
	}

	public boolean delRoleById(String id) {
		boolean result = false;
		try {
			result = qr.update(DEL_ROLE_BY_ID, id) == 1;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return result;
	}

	public boolean delRolesByIds(String[] ids) {
		boolean result = false;
		List<Object[]> params = new LinkedList<Object[]>();
		for (int i = 0; i < ids.length; i++) params.add(new Object[] {ids[i]});
		try {
			int[] status = qr.batch(DEL_ROLE_BY_ID, params.toArray(new Object[0][0]));
			for (int i = 0; i < status.length; i++)
				if (status[i] != 1) result = false;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return result;
	}
	
	public boolean updateRole(RoleBean role) {
		boolean result = false;
		try {
			List<Object> params = new ArrayList<Object>();
			for (Field field : role.getClass().getDeclaredFields()) {
				String fieldName = field.getName();
				if (!"menus".equalsIgnoreCase(fieldName))
					params.add(BeanUtils.getProperty(role, field.getName()));
			}
			params.add(params.remove(0));
			result = qr.update(UPDATE_ROLE, params.toArray(new Object[0])) == 1;
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return result;
	}

	public List<RoleBean> findAllRoles() {
		List<RoleBean> result = null;
		try {
			result = qr.query(FIND_ALL_ROLES, new BeanListHandler<RoleBean>(RoleBean.class));
			if (result != null) {
				for (RoleBean role : result) {
					List<MenuBean> menus = qr.query(FIND_MENUS_BY_ROLE_ID, new BeanListHandler<MenuBean>(MenuBean.class), new Object[] {role.getId()});
					role.setMenus(menus);
				}
			}	
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return result;
	}

	public RoleBean findRolesById(String id) {
		RoleBean result = null;
		try {
			result = qr.query(FIND_ROLE_BY_ID, new BeanHandler<RoleBean>(RoleBean.class), new Object[] {id});
			if (result != null) {
				List<MenuBean> menus = qr.query(FIND_MENUS_BY_ROLE_ID, new BeanListHandler<MenuBean>(MenuBean.class), new Object[] {id});
				result.setMenus(menus);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return result;
	}

	public List<RoleBean> findRolesByRange(int startRowId, int endRowId) {
		List<RoleBean> result = null;
		try {
			result = qr.query(FIND_ROLE_BY_RANGE, new BeanListHandler<RoleBean>(RoleBean.class), new Object[] {startRowId, endRowId});
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return result;
	}

}

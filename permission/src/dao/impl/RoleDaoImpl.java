package dao.impl;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
import formbean.impl.UpdateRoleFormBean;

public class RoleDaoImpl implements RoleDao {
	
	private final String FIND_ALL_ROLES = "SELECT id, name, description FROM role";
	private final String ADD_ROLE = "INSERT INTO role (id, name, description) VALUES (?, ?, ?)";
	private final String FIND_ROLE_BY_RANGE = "SELECT id, name, description, row_id FROM (SELECT r.*, ROWNUM as row_id FROM (SELECT id, name, description FROM role) r) WHERE row_id BETWEEN ? AND ?";
	private final String GET_NUMBER_OF_ROLES = "SELECT count(*) FROM role";
	private final String DEL_ROLE_BY_ID = "DELETE FROM role WHERE id = ?";
	private final String FIND_ROLE_BY_ID = "SELECT id, name, description FROM role WHERE id = ?";
	private final String UPDATE_ROLE = "UPDATE role SET name = ?, description = ? WHERE id = ?";
	private final String FIND_MENUS_BY_ROLE_ID = "SELECT id, title, uri, description FROM menu WHERE id IN (SELECT m_id FROM role_menu WHERE r_id = ?)";
	
	// ʹ��c3p0����Դ��ֱ�Ӵӳ����õ������ú���Զ��ر�
	private QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());

	@Override
	public List<RoleBean> findAllRoles() {
		try {
			return qr.query(FIND_ALL_ROLES, new BeanListHandler<RoleBean>(RoleBean.class));
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public boolean addRole(RoleBean role) {
		boolean result = false;
		List<Object> params = new LinkedList<Object>();
		Field[] fields = role.getClass().getDeclaredFields();
		try {
			for (Field field : fields) {
				String fieldName = field.getName();
				if (!fieldName.equalsIgnoreCase("menus"))
					params.add(BeanUtils.getProperty(role, field.getName()));
			}
			result = qr.update(ADD_ROLE, params.toArray(new Object[0])) == 1;
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return result;
	}
	
	@Override
	public int getNumberOfRoles() {
		try {
			return ((Number) qr.query(GET_NUMBER_OF_ROLES, new ScalarHandler(1))).intValue();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List findRolesByRange(int startRowId, int endRowId) {
		try {
			return qr.query(FIND_ROLE_BY_RANGE, new BeanListHandler<RoleBean>(RoleBean.class), new Object[] {startRowId, endRowId});
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public boolean delRoleById(String id) {
		try {
			return qr.update(DEL_ROLE_BY_ID, id) == 1;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
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

	@Override
	public boolean updateRole(RoleBean role) {
		List<Object> params = null;
		try {
			for (Field field : role.getClass().getDeclaredFields()) {
				String fieldName = field.getName();
				if (params == null) params = new ArrayList<Object>();
				if (!"menus".equalsIgnoreCase(fieldName))
					params.add(BeanUtils.getProperty(role, field.getName()));
			}
			params.add(params.remove(0));
			return qr.update(UPDATE_ROLE, params.toArray(new Object[0])) == 1;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public boolean delRolesByIds(String[] ids) {
		boolean result = false;
		List<Object[]> params = new LinkedList<Object[]>();
		for (int i = 0; i < ids.length; i++) {
			params.add(new Object[] {ids[i]});
		}
		try {
			qr.batch(DEL_ROLE_BY_ID, params.toArray(new Object[0][0]));
			result = true;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return result;
	}

}

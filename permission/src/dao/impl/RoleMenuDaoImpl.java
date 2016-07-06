package dao.impl;

import java.sql.SQLException;
import java.util.Arrays;

import org.apache.commons.dbutils.QueryRunner;

import com.benqcz.utils.jdbc.C3P0Utils;

import dao.RoleMenuDao;
import exception.DaoException;

public class RoleMenuDaoImpl implements RoleMenuDao {
	
	private final String DEL_RELATIONS_BY_ROLE_ID = "DELETE FROM role_menu WHERE r_id = ?";
	private final String ADD_RELATIONS = "INSERT INTO role_menu (r_id, m_id) VALUES (?, ?)";
	private final String DEL_RELATIONS_BY_MENU_ID = "DELETE FROM role_menu WHERE m_id = ?";
	
	private QueryRunner qr = new QueryRunner();

	@Override
	public boolean addRelations(String rId, String[] mIds) {
		boolean result = false;
		String[][] params = new String[mIds.length][];
		for (int i = 0; i < mIds.length; i++) {
			params[i] = new String[] {rId, mIds[i]};
		}
		try {
			int[] status = qr.batch(C3P0Utils.open(), ADD_RELATIONS, params);
			for (int i = 0 ; i < status.length; i++)
				if (status[i] < 0) result = false;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			C3P0Utils.close();
		}
		return result;
	}

	@Override
	public boolean delRelationsByMenuId(String id) {
		try {
			return qr.update(C3P0Utils.open(), DEL_RELATIONS_BY_MENU_ID, id) >= 0;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			C3P0Utils.close();
		}
	}

	@Override
	public boolean delRelationsByRoleId(String id) {
		try {
			return qr.update(C3P0Utils.open(), DEL_RELATIONS_BY_ROLE_ID, id) >= 0;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			C3P0Utils.close();
		}
	}

}

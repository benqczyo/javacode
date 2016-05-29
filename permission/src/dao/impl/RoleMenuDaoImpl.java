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
	
	private QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());

	@Override
	public boolean delRelationsByRoleId(String id) {
		try {
			qr.update(DEL_RELATIONS_BY_ROLE_ID, id);
			return true;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public boolean addRelations(String rId, String[] mIds) {
		boolean result = false;
		String[][] params = new String[mIds.length][];
		for (int i = 0; i < mIds.length; i++) {
			params[i] = new String[] {rId, mIds[i]};
		}
		try {
			qr.batch(ADD_RELATIONS, params);
			result = true;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return result;
	}

}

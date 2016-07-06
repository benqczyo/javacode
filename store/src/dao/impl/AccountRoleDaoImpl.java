package dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.benqcz.utils.jdbc.C3P0Utils;

import dao.AccountRoleDao;
import exception.DaoException;

public class AccountRoleDaoImpl implements AccountRoleDao {
	
	private final String ADD_RELATIONS = "INSERT INTO account_role (a_id, r_id) VALUES (?, ?)";
	private final String DEL_RELATIONS_BY_ACCOUNT_ID = "DELETE FROM account_role WHERE a_id = ?";
	private final String DEL_RELATIONS_BY_ROLE_ID = "DELETE FROM account_role WHERE r_id = ?";
	
	private QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());

	public boolean addRelations(String aId, String[] rIds) {
		boolean result = false;
		String[][] params = new String[rIds.length][];
		for (int i = 0; i < rIds.length; i++) {
			params[i] = new String[] {aId, rIds[i]};
		}
		try {
			int[] status = qr.batch(ADD_RELATIONS, params);
			for (int i = 0; i < status.length; i++) {
				if (status[i] != 1) result = false; 
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return result;
	}

	public boolean delRelationsByAccountId(String id) {
		boolean result = false;
		try {
			result = qr.update(DEL_RELATIONS_BY_ACCOUNT_ID, id) >= 0;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return result;
	}

	public boolean delRelationsByRoleId(String id) {
		boolean result = false;
		try {
			result = qr.update(DEL_RELATIONS_BY_ROLE_ID, id) >= 0;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return result;
	}

}

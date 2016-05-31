package dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.benqcz.utils.jdbc.C3P0Utils;

import dao.AccountRoleDao;
import exception.DaoException;

public class AccountRoleDaoImpl implements AccountRoleDao {

	private final String ADD_RELATIONS = "INSERT INTO account_role (a_id, r_id) VALUES (?, ?)";
	private final String DEL_RELATIONS_BY_ACCOUNT_ID = "DELETE FROM account_role WHERE a_id = ?";
	
	private QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
	
	@Override
	public boolean addRelations(String aId, String[] rIds) {
		boolean result = false;
		String[][] params = new String[rIds.length][];
		for (int i = 0; i < rIds.length; i++) {
			params[i] = new String[] {aId, rIds[i]};
		}
		try {
			qr.batch(ADD_RELATIONS, params);
			result = true;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return result;
	}

	@Override
	public boolean delRelationsByAccountId(String id) {
		try {
			qr.update(DEL_RELATIONS_BY_ACCOUNT_ID, id);
			return true;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

}

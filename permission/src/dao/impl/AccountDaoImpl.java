package dao.impl;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.benqcz.utils.jdbc.C3P0Utils;

import dao.AccountDao;
import domain.AccountBean;
import domain.RoleBean;
import exception.DaoException;

public class AccountDaoImpl implements AccountDao {
	
	private final String GET_NUMBER_OF_ACCOUNTS = "SELECT count(*) FROM account";
	private final String ADD_ACCOUNT = "INSERT INTO account (id, name, password) VALUES (?, ?, ?)";
	private final String FIND_ACCOUNTS_BY_RANGE = "SELECT id, name, password, row_id FROM (SELECT a.*, ROWNUM as row_id FROM (SELECT id, name, password FROM account) a) WHERE row_id BETWEEN ? AND ?";
	private final String FIND_ALL_ACCOUNTS = "SELECT id, name, password FROM account";
	
	private QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());

	@Override
	public List<AccountBean> findAllAccounts() {
		try {
			return qr.query(FIND_ALL_ACCOUNTS, new BeanListHandler<AccountBean>(AccountBean.class));
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<AccountBean> findAccountsByRange(int startRowId, int endRowId) {
		try {
			return qr.query(FIND_ACCOUNTS_BY_RANGE, new BeanListHandler<AccountBean>(AccountBean.class), new Object[] {startRowId, endRowId});
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int getNumberOfAccounts() {
		try {
			return ((Number) qr.query(GET_NUMBER_OF_ACCOUNTS, new ScalarHandler(1))).intValue();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public boolean addAccount(AccountBean account) {
		boolean result = false;
		List<Object> params = new LinkedList<Object>();
		Field[] fields = account.getClass().getDeclaredFields();
		try {
			for (Field field : fields) {
				String fieldName = field.getName();
				if (!fieldName.equalsIgnoreCase("roles"))
					params.add(BeanUtils.getProperty(account, field.getName()));
			}
			result = qr.update(ADD_ACCOUNT, params.toArray(new Object[0])) == 1;
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return result;
	}

}

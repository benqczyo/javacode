package dao.impl;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import utils.DBCPUtils;

import dao.AccountDao;
import domain.AccountBean;
import exception.DaoException;

public class AccountDaoImpl implements AccountDao {

	private static final String ADD_ACCOUNT = "INSERT INTO account2 (id, name, password, email, code, actived) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String DEL_ACCOUNT_BY_ID = "DELETE FROM account2 WHERE id = ?";
	private static final String DEL_ACCOUNT_BY_NAME = "DELETE FROM account2 WHERE name = ?";
	private static final String UPDATE_ACCOUNT = "UPDATE account2 SET id = ?, name = ?, password = ?, email = ?, code = ?, actived = ? WHERE id = ?";
	private static final String FIND_ALL_ACCOUNTS = "SELECT id, name, password, email, code, actived FROM account2";
	private static final String FIND_ACCOUNT_BY_ID = "SELECT id, name, password, email, code, actived FROM account2 WHERE id = ?";
	private static final String FIND_ACCOUNT_BY_NAME = "SELECT id, name, password, email, code, actived FROM account2 WHERE name = ?";
	private static final String FIND_ACCOUNT_BY_CODE = "SELECT id, name, password, email, code, actived FROM account2 WHERE code = ?";
	private static final String FIND_ACCOUNT = "SELECT id, name, password, email, code, actived FROM account2 WHERE name = ? AND password = ?";
	
	private QueryRunner qr = new QueryRunner(DBCPUtils.getDataSource());

	@Override
	public boolean addAccount(AccountBean account) {
		boolean result = false;
		List<Object> params = new ArrayList<Object>();
		try {
			Field[] fields = account.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				params.add(field.get(account));
			}
			result = qr.update(ADD_ACCOUNT, params.toArray()) == 1;
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return result;
	}

	@Override
	public boolean delAccountById(String id) {
		try {
			return qr.update(DEL_ACCOUNT_BY_ID, id) == 1;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public boolean delAccountByName(String name) {
		try {
			return qr.update(DEL_ACCOUNT_BY_NAME, name) == 1;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<AccountBean> findAllAccounts() {
		List<AccountBean> result = null;
		try {
			result = qr.query(FIND_ALL_ACCOUNTS,
					new BeanListHandler<AccountBean>(AccountBean.class));
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return result;
	}

	@Override
	public AccountBean findAccountById(String id) {
		AccountBean result = null;
		try {
			result = qr.query(FIND_ACCOUNT_BY_ID, new BeanHandler<AccountBean>(
					AccountBean.class), id);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return result;
	}

	@Override
	public AccountBean findAccountByName(String name) {
		AccountBean result = null;
		try {
			result = qr.query(FIND_ACCOUNT_BY_NAME, new BeanHandler<AccountBean>(
					AccountBean.class), name);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return result;
	}

	@Override
	public AccountBean findAccount(String name, String password) {
		AccountBean result = null;
		try {
			result = qr.query(FIND_ACCOUNT, new BeanHandler<AccountBean>(
					AccountBean.class), name, password);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return result;
	}

	@Override
	public boolean updateAccount(AccountBean account) {
		boolean result = false;
		List<Object> params = new ArrayList<Object>();
		try {
			Field[] fields = account.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				params.add(field.get(account));
			}
			params.add(params.get(0));
			result = qr.update(UPDATE_ACCOUNT, params.toArray()) == 1;
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return result;
	}

	@Override
	public AccountBean findAccountByCode(String code) {
		AccountBean result = null;
		try {
			result = qr.query(FIND_ACCOUNT_BY_CODE, new BeanHandler<AccountBean>(
					AccountBean.class), code);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return result;
	}

}

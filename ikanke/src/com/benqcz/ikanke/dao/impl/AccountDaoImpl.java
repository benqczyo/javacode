package com.benqcz.ikanke.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.benqcz.ikanke.dao.AbstractDao;
import com.benqcz.ikanke.dao.AccountDao;
import com.benqcz.ikanke.domain.impl.AccountBean;
import com.benqcz.ikanke.exception.AccountDeleteFailedException;
import com.benqcz.ikanke.exception.AccountExistsException;
import com.benqcz.ikanke.exception.AccountFindFailedException;
import com.benqcz.ikanke.exception.AccountRegisterFailedException;
import com.benqcz.ikanke.utils.DBCPUtils;

public class AccountDaoImpl extends AbstractDao implements AccountDao {

	private static final String ADD_ACCOUNT = "INSERT INTO iaccount (id, code, name, password, cellphone, email, address, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String DEL_ACCOUNT_BY_NAME = "DELETE FROM iaccount WHERE name = ?";
	private static final String UPDATE_ACCOUNT = "";
	private static final String FIND_ALL_ACCOUNTS = "SELECT id, code, name, password, cellphone, email, address, status FROM iaccount";
	private static final String FIND_ACCOUNT_BY_NAME = "SELECT id, code, name, password, cellphone, email, address, status FROM iaccount WHERE name = ?";
	private static final String FIND_ACCOUNT = "SELECT id, code, name, password, cellphone, email, address, status FROM iaccount WHERE name = ? AND password = ?";

	@Override
	public boolean addAccount(AccountBean account) {
		try {
			return qr.update(DBCPUtils.open(), ADD_ACCOUNT, account.getId(),
					account.getCode(), account.getName(),
					account.getPassword(), account.getCellphone(),
					account.getEmail(), account.getAddress(),
					account.getStatus()) == 1;
		} catch (SQLException e) {
			throw new AccountRegisterFailedException(e);
		}
	}

	@Override
	public boolean delAccountByName(String name) {
		try {
			return qr.update(DBCPUtils.open(), DEL_ACCOUNT_BY_NAME, name) == 1;
		} catch (SQLException e) {
			throw new AccountDeleteFailedException(e);
		}
	}

	@Override
	public boolean updateAccount(AccountBean account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<AccountBean> findAllAccounts() {
		try {
			return qr.query(DBCPUtils.open(), FIND_ALL_ACCOUNTS,
					new BeanListHandler<AccountBean>(AccountBean.class));
		} catch (SQLException e) {
			throw new AccountFindFailedException(e);
		}
	}

	@Override
	public AccountBean findAccountByName(String name) {
		try {
			return qr.query(DBCPUtils.open(), FIND_ACCOUNT_BY_NAME,
					new BeanHandler<AccountBean>(AccountBean.class));
		} catch (SQLException e) {
			throw new AccountFindFailedException(e);
		}
	}

	@Override
	public AccountBean findAccount(String name, String password) {
		try {
			return qr.query(DBCPUtils.open(), FIND_ACCOUNT,
					new BeanHandler<AccountBean>(AccountBean.class), name,
					password);
		} catch (SQLException e) {
			throw new AccountFindFailedException(e);
		}
	}
	
}

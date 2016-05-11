package com.benqcz.account.dao.impl;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.benqcz.account.dao.AccountDao;
import com.benqcz.account.domain.Account;
import com.benqcz.dbutils.C3P0Utils;

public class AccountDaoImpl implements AccountDao {
	
	private final String ADD_ACCOUNT = "INSERT INTO account (id, name, balance) VALUES (?, ?, ?)";
	private final String FIND_ACCOUNT_BY_ID = "SELECT id, name, balance FROM account WHERE id = ?";
	private final String UPDATE_ACCOUNT = "UPDATE account SET id = ?, name = ?, balance = ? WHERE id = ?";
	
	private QueryRunner qr = new QueryRunner();
	
	@Override
	public boolean addAccount(Account account) {
		boolean result = false;
		List<Object> params = new LinkedList<Object>();
		try {
			for (Field field : account.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				params.add(field.get(account));
			}
			result = qr.update(C3P0Utils.open(), ADD_ACCOUNT, params.toArray()) == 1;
		} catch (Exception e) {
			throw new AccountDaoException(e);
		}
		return result;
	}
	
	@Override
	public Account findAccountById(int id) {
		Account result = null;
		try {
			result = qr.query(C3P0Utils.open(), FIND_ACCOUNT_BY_ID, new BeanHandler<Account>(Account.class), id);
		} catch (SQLException e) {
			throw new AccountDaoException(e);
		}
		return result;
	}

	@Override
	public boolean updateAccount(Account account) {
		boolean result = false;
		List<Object> params = new LinkedList<Object>();
		try {
			for (Field field : account.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				params.add(field.get(account));
			}
			params.add(account.getId());
			result = qr.update(C3P0Utils.open(), UPDATE_ACCOUNT, params.toArray()) == 1;
		} catch (Exception e) {
			throw new AccountDaoException(e);
		}
		return result;
	}

}

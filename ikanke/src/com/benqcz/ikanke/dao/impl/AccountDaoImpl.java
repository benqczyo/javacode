package com.benqcz.ikanke.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.benqcz.ikanke.dao.AbstractDao;
import com.benqcz.ikanke.dao.AccountDao;
import com.benqcz.ikanke.domain.impl.AccountBean;
import com.benqcz.ikanke.exception.AccountExistsException;
import com.benqcz.ikanke.exception.AccountRegisterFailedException;
import com.benqcz.ikanke.utils.DBCPUtils;

public class AccountDaoImpl extends AbstractDao implements AccountDao {

	private static final String ADD_ACCOUNT = "INSERT INTO iaccount (id, code, name, password, cellphone, email, address) VALUES (?, ?, ?, ?, ?, ?, ?)";

	@Override
	public boolean addAccount(AccountBean account) {
		try {
			return qr.update(DBCPUtils.open(), ADD_ACCOUNT, account.getId(),
					account.getCode(), account.getName(),
					account.getPassword(), account.getCellphone(),
					account.getEmail(), account.getAddress()) == 1;
		} catch (SQLException e) {
			throw new AccountRegisterFailedException(e);
		}
	}

	@Override
	public boolean delAccountByName(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAccount(AccountBean account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<AccountBean> findAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountBean findAccountByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountBean findAccount(String name, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}

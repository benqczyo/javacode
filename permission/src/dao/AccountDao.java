package dao;

import java.util.List;

import domain.AccountBean;

public interface AccountDao {
	int getNumberOfAccounts();
	boolean addAccount(AccountBean account);
	List<AccountBean> findAllAccounts();
	List<AccountBean> findAccountsByRange(int startRowId, int endRowId);
	AccountBean findAccountById(String id);
	AccountBean findAccountByName(String name);
}

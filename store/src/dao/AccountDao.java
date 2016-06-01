package dao;

import java.util.List;

import domain.AccountBean;

public interface AccountDao {
	int getNumberOfAccounts();
	boolean addAccount(AccountBean account);
	AccountBean findAccount(String name, String password);
	AccountBean findAccountById(String id);
	List<AccountBean> findAccountsByRange(int startRowId, int endRowId);
	List<AccountBean> findAllAccounts();
}

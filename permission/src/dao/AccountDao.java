package dao;

import java.util.List;

import domain.AccountBean;

public interface AccountDao {
	boolean addAccount(AccountBean account);
	AccountBean findAccount(String name, String password);
	AccountBean findAccountById(String id);
	List<AccountBean> findAccountsByRange(int startRowId, int endRowId);
	List<AccountBean> findAllAccounts();
	int getNumberOfAccounts();
}

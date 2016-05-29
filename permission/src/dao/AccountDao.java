package dao;

import java.util.List;

import domain.AccountBean;

public interface AccountDao {
	int getNumberOfAccounts();
	List<AccountBean> findAllAccounts();
	List<AccountBean> findAccountsByRange(int startRowId, int endRowId);
}

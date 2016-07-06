package dao;

import java.util.List;

import domain.AccountBean;

public interface AccountDao {
	boolean addAccount(AccountBean account);
	boolean delAccountById(String id);
	boolean delAccountByName(String name);
	boolean updateAccount(AccountBean account);
	List<AccountBean> findAllAccounts();
	AccountBean findAccountById(String id);
	AccountBean findAccountByName(String name);
	AccountBean findAccount(String name, String password);
	AccountBean findAccountByCode(String code);
}

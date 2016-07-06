package service;

import domain.AccountBean;

public interface AccountService {
	boolean register(AccountBean account);
	AccountBean login(String name, String password);
	boolean enableAccount(String code);
}

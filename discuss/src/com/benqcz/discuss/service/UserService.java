package com.benqcz.discuss.service;

import com.benqcz.discuss.domain.User;
import com.benqcz.discuss.exception.UserExistException;

public interface UserService {
	/**
	 * �û�ע��
	 * @param user Ҫע���User����
	 * @return ע��ɹ�����ע��User����ʧ���׳��쳣
	 */
	public abstract User register(User user) throws UserExistException;
	
	/**
	 * �û���¼
	 * @param userName ��½�û���
	 * @param password ��½�û�����
	 * @return ��½�ɹ����ص�½User����ʧ�ܷ���null
	 */
	public abstract User login(String userName, String password);
}

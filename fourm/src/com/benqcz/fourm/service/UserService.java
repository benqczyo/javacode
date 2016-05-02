package com.benqcz.fourm.service;

import com.benqcz.fourm.domain.UserBean;
import com.benqcz.fourm.exception.UserExistException;

public interface UserService {
	/**
	 * ע���û�
	 * @param user ע���û���UserBean����
	 * @return ע��ɹ������û�UserBean����ʧ���׳�UserExistException
	 * @throws UserExistException 
	 */
	public abstract UserBean register(UserBean user) throws UserExistException;
	/**
	 * �û���¼
	 * @param username �û���
	 * @param password ����
	 * @return ��½�ɹ������û�UserBean����ʧ�ܷ���null
	 */
	public abstract UserBean login(String username, String password);
	
}

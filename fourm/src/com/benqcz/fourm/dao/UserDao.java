package com.benqcz.fourm.dao;

import com.benqcz.fourm.domain.UserBean;

public interface UserDao {
	/**
	 * ����û�
	 * @param user ����û���UserBean����
	 * @return ��ӳɹ���������û���UserBean����ʧ�ܷ���null
	 * @exception �׳�RuntimeException
	 */
	public abstract UserBean addUser(UserBean user);
	/**
	 * �����û��������û�
	 * @param username �û���
	 * @return �û����ڷ����û�UserBean����ʧ�ܷ���null
	 * @exception �׳�RuntimeException
	 */
	public abstract UserBean findUserByName(String username);
	/**
	 * �����û�������������û�
	 * @param username �û���
	 * @param password ����
	 * @return �û����ڷ����û�UserBean����ʧ�ܷ���null
	 * @exception �׳�RuntimeException
	 */
	public abstract UserBean findUser(String username, String password);
}

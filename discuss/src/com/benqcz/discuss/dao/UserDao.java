package com.benqcz.discuss.dao;

import com.benqcz.discuss.domain.User;

public interface UserDao {
	/**
	 * �����û���Ϣ��xml���ݿ�
	 * @param user User����
	 * @return true�ɹ� falseʧ��
	 */
	public abstract boolean addUser(User user);
	
	/**
	 * �����û��������û�
	 * @param name �û���
	 * @return ���ҵ���User����û���ҵ�����null
	 */
	public abstract User findUserByName(String userName);
	
	/**
	 * �����û�����������û�
	 * @param name �û���
	 * @param password ����
	 * @return ���ҵ���User����û���ҵ�����null
	 */
	public abstract User findUser(String userName, String password);
}

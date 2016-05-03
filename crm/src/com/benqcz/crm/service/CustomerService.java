package com.benqcz.crm.service;

import java.util.Map;

import com.benqcz.crm.domain.CustomerBean;

public interface CustomerService {
	/**
	 * ��ӿͻ�
	 * @param user ��ӿͻ���CustomerBean����
	 * @return ��ӳɹ�������ӿͻ�CustomerBean����ʧ�ܷ���null
	 */
	public abstract CustomerBean addCustomer(CustomerBean customer);
	/**
	 * ɾ���ͻ�
	 * @param id ɾ���ͻ���id
	 * @return ɾ���ɹ�����true��ʧ�ܷ���false
	 */
	public abstract boolean deleteCustomerById(int id);
	/**
	 * �޸Ŀͻ�
	 * @param customer �޸Ŀͻ���ϢCustomerBean����
	 * @return �ɹ������޸Ŀͻ���ʧ�ܷ���null
	 */
	public abstract CustomerBean updateCustomer(CustomerBean customer);
	/**
	 * ����ָ���ͻ�
	 * @param id �ͻ�id
	 * @return �ɹ����ز��ҵ��Ŀͻ���ʧ�ܷ���null
	 */
	public abstract CustomerBean findCustomerById(int id);
	/**
	 * �������пͻ�
	 * @return �ɹ�����map���󣬼�Ϊ�ͻ�id��ֵΪCustomerBean����
	 */
	public abstract Map<Integer, CustomerBean> findCustomer();
}

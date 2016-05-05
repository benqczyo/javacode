package com.benqcz.crm.dao;

import java.util.List;
import java.util.Map;

import com.benqcz.crm.domain.CustomerBean;

public interface CustomerDao {
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
	public abstract List<CustomerBean> findCustomer();
	/**
	 * ɾ�������ͻ���¼
	 * @param ids �ͻ�id����
	 * @return �ɹ�����true,ʧ�ܷ���false
	 */
	public abstract boolean deleteMutilCustomer(String[] ids);
	/**
	 * �������ݿ��¼��
	 * @return �������ݿ��¼��,ʧ�ܷ���-1
	 */
	public abstract int getNumberOfCustomers();
	/**
	 * ��ҳ���ؿͻ���
	 * @return �ɹ��ͻ�Map,ʧ�ܷ���null
	 */
	public abstract List<CustomerBean> findCustomersByPageId(int pageId);
}

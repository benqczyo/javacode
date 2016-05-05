package com.benqcz.crm.service;

import java.util.Map;

import com.benqcz.crm.domain.CustomerBean;

public interface CustomerService {
	/**
	 * 添加客户
	 * @param user 添加客户的CustomerBean对象
	 * @return 添加成功返回添加客户CustomerBean对象，失败返回null
	 */
	public abstract CustomerBean addCustomer(CustomerBean customer);
	/**
	 * 删除客户
	 * @param id 删除客户的id
	 * @return 删除成功返回true，失败返回false
	 */
	public abstract boolean deleteCustomerById(int id);
	/**
	 * 修改客户
	 * @param customer 修改客户信息CustomerBean对象
	 * @return 成功返回修改客户，失败返回null
	 */
	public abstract CustomerBean updateCustomer(CustomerBean customer);
	/**
	 * 查找指定客户
	 * @param id 客户id
	 * @return 成功返回查找到的客户，失败返回null
	 */
	public abstract CustomerBean findCustomerById(int id);
	/**
	 * 查找所有客户
	 * @return 成功返回map对象，键为客户id，值为CustomerBean对象
	 */
	public abstract Map<Integer, CustomerBean> findCustomer();
	/**
	 * 删除多条客户记录
	 * @param ids 客户id数组
	 * @return 成功返回true,失败返回false
	 */
	public abstract boolean deleteMutilCustomer(String[] ids);
	/**
	 * 返回客户记录数
	 * @return 客户记录数
	 */
	public abstract int getNumberOfCustomers();
	/**
	 * 分页查询用户
	 * @param pageId 页面id，1开始
	 * @return 返回客户Map,失败返回null
	 */
	public abstract Map<Integer, CustomerBean> selectCustomerByPageId(int pageId);
}

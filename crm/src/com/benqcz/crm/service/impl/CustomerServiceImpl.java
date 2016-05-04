package com.benqcz.crm.service.impl;

import java.util.Map;

import com.benqcz.crm.dao.CustomerDao;
import com.benqcz.crm.dao.impl.CustomerDaoImpl;
import com.benqcz.crm.domain.CustomerBean;
import com.benqcz.crm.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {
	
	private CustomerDao dao = new CustomerDaoImpl();

	@Override
	public CustomerBean addCustomer(CustomerBean customer) {
		return dao.addCustomer(customer);
	}

	@Override
	public boolean deleteCustomerById(int id) {
		return dao.deleteCustomerById(id);
	}

	@Override
	public Map<Integer, CustomerBean> findCustomer() {
		return dao.findCustomer();
	}

	@Override
	public CustomerBean findCustomerById(int id) {
		return dao.findCustomerById(id);
	}

	@Override
	public CustomerBean updateCustomer(CustomerBean customer) {
		return dao.updateCustomer(customer);
	}

	@Override
	public boolean deleteMutilCustomer(String[] ids) {
		return dao.deleteMutilCustomer(ids);
	}

}

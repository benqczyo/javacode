package com.benqcz.crm.dao.impl;

import java.sql.Connection;
import java.util.List;
import com.benqcz.crm.dao.CustomerDao;
import com.benqcz.crm.domain.CustomerBean;
import com.benqcz.crm.exception.DaoException;
import com.benqcz.crm.utils.C3P0Utils;
import com.benqcz.crm.utils.DBCPUtils;

public abstract class AbstractCustomerDaoImpl implements CustomerDao {
	
	protected abstract CustomerBean addCustomer(Connection conn, CustomerBean customer);
	protected abstract boolean deleteCustomerById(Connection conn, int id);
	protected abstract CustomerBean updateCustomer(Connection conn, CustomerBean customer);
	protected abstract List<CustomerBean> findCustomer(Connection conn);
	protected abstract CustomerBean findCustomerById(Connection conn, int id);
	protected abstract boolean deleteMutilCustomer(Connection conn, String[] ids);
	protected abstract int getNumberOfCustomers(Connection conn);
	protected abstract List<CustomerBean> findCustomersByRange(Connection conn, int startIndex, int endIndex);

	@Override
	public CustomerBean addCustomer(CustomerBean customer) {
		try {
			return addCustomer(C3P0Utils.open(), customer);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			C3P0Utils.close();
		}
	}

	@Override
	public boolean deleteCustomerById(int id) {
		try {
			return deleteCustomerById(C3P0Utils.open(), id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			C3P0Utils.close();
		}
	}

	@Override
	public List<CustomerBean> findCustomer() {
		try {
			return findCustomer(C3P0Utils.open());
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			C3P0Utils.close();
		}
	}

	@Override
	public CustomerBean findCustomerById(int id) {
		try {
			return findCustomerById(C3P0Utils.open(), id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			C3P0Utils.close();
		}
	}

	@Override
	public CustomerBean updateCustomer(CustomerBean customer) {
		try {
			return updateCustomer(C3P0Utils.open(), customer);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			C3P0Utils.close();
		}
	}
	@Override
	public boolean deleteMutilCustomer(String[] ids) {
		try {
			return deleteMutilCustomer(C3P0Utils.open(), ids);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			C3P0Utils.close();
		}
	}
	@Override
	public int getNumberOfCustomers() {
		try {
			return getNumberOfCustomers(C3P0Utils.open());
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			C3P0Utils.close();
		}
	}
	@Override
	public List<CustomerBean> findCustomersByRange(int startId, int endIndex) {
		try {
			return findCustomersByRange(C3P0Utils.open(), startId, endIndex);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			C3P0Utils.close();
		}
	}

}

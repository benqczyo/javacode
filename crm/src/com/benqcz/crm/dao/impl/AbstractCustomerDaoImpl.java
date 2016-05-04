package com.benqcz.crm.dao.impl;

import java.sql.Connection;
import java.util.Map;

import com.benqcz.crm.dao.CustomerDao;
import com.benqcz.crm.domain.CustomerBean;
import com.benqcz.crm.exception.DaoException;
import com.benqcz.crm.utils.DBUtils;

import oracle.sql.CustomDatum;

public abstract class AbstractCustomerDaoImpl implements CustomerDao {
	
	protected abstract CustomerBean addCustomer(Connection conn, CustomerBean customer);
	protected abstract boolean deleteCustomerById(Connection conn, int id);
	protected abstract CustomerBean updateCustomer(Connection conn, CustomerBean customer);
	protected abstract Map<Integer, CustomerBean> findCustomer(Connection conn);
	protected abstract CustomerBean findCustomerById(Connection conn, int id);
	protected abstract boolean deleteMutilCustomer(Connection conn, String[] ids);

	@Override
	public CustomerBean addCustomer(CustomerBean customer) {
		try {
			return addCustomer(DBUtils.open(), customer);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			DBUtils.close();
		}
	}

	@Override
	public boolean deleteCustomerById(int id) {
		try {
			return deleteCustomerById(DBUtils.open(), id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			DBUtils.close();
		}
	}

	@Override
	public Map<Integer, CustomerBean> findCustomer() {
		try {
			return findCustomer(DBUtils.open());
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			DBUtils.close();
		}
	}

	@Override
	public CustomerBean findCustomerById(int id) {
		try {
			return findCustomerById(DBUtils.open(), id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			DBUtils.close();
		}
	}

	@Override
	public CustomerBean updateCustomer(CustomerBean customer) {
		try {
			return updateCustomer(DBUtils.open(), customer);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			DBUtils.close();
		}
	}
	@Override
	public boolean deleteMutilCustomer(String[] ids) {
		try {
			return deleteMutilCustomer(DBUtils.open(), ids);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			DBUtils.close();
		}
	}

}

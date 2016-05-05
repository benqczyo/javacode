package com.benqcz.crm.dao.impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.benqcz.crm.domain.CustomerBean;
import com.benqcz.crm.exception.DaoException;

public class CustomerDaoImpl extends AbstractCustomerDaoImpl {

	private static final String ADD_CUSTOMER = "INSERT INTO customer (id, name, gender, birthday, cellphone, email, preference, type, description) VALUES (customer_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_CUSTOMER = "UPDATE customer SET name = ?, gender = ?, birthday = ?, cellphone = ?, email = ?, preference = ?, type = ?, description = ? WHERE id = ?";
	private static final String DELETE_CUSTOMER_BY_ID = "DELETE FROM customer WHERE id = ?";
	private static final String FIND_CUSTOMER = "SELECT id, name, gender, birthday, cellphone, email, preference, type, description FROM customer ORDER BY id";
	private static final String FIND_CUSTOMER_BY_ID = "SELECT id, name, gender, birthday, cellphone, email, preference, type, description FROM customer WHERE id = ?";
	private static final String GET_NUMBER_OF_CUSTOMERS = "SELECT count(*) AS customers FROM customer";
	
	@Override
	protected CustomerBean addCustomer(Connection conn, CustomerBean customer) {
		CustomerBean result = null;
		try {
			PreparedStatement st = conn.prepareStatement(ADD_CUSTOMER, new String[] {"id"});
			st.setString(1, customer.getName());
			st.setInt(2, customer.getGender());
			st.setDate(3, new java.sql.Date(customer.getBirthday().getTime()));
			st.setString(4, customer.getCellphone());
			st.setString(5, customer.getEmail());
			st.setString(6, customer.getPreference());
			st.setInt(7, customer.getType());
			st.setString(8, customer.getDescription());
			st.setInt(9, customer.getId());
			if (st.executeUpdate() > 0) {
				ResultSet rs = st.getGeneratedKeys();
				rs.next();
				customer.setId(rs.getInt(1));
				result = customer;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
		return result;
	}

	@Override
	protected boolean deleteCustomerById(Connection conn, int id) {
		boolean result = false;
		try {
			PreparedStatement st = conn.prepareStatement(DELETE_CUSTOMER_BY_ID);
			st.setInt(1, id);
			result = st.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
		return result;
	}

	@Override
	protected List<CustomerBean> findCustomer(Connection conn) {
		List<CustomerBean> result = null;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(FIND_CUSTOMER);
			while (rs.next()) {
				if (result == null) result = new ArrayList<CustomerBean>();
				CustomerBean customer = new CustomerBean();
				customer.setId(rs.getInt("id"));
				customer.setName(rs.getString("name"));
				customer.setGender(rs.getInt("gender"));
				customer.setBirthday(rs.getDate("birthday"));
				customer.setCellphone(rs.getString("cellphone"));
				customer.setEmail(rs.getString("email"));
				customer.setPreference(rs.getString("preference"));
				customer.setType(rs.getInt("type"));
				customer.setDescription(rs.getString("description"));
				result.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
		return result;
	}

	@Override
	protected CustomerBean findCustomerById(Connection conn, int id) {
		CustomerBean result = null;
		try {
			PreparedStatement st = conn.prepareStatement(FIND_CUSTOMER_BY_ID);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				result = new CustomerBean();
				result.setId(rs.getInt("id"));
				result.setName(rs.getString("name"));
				result.setGender(rs.getInt("gender"));
				result.setBirthday(rs.getDate("birthday"));
				result.setCellphone(rs.getString("cellphone"));
				result.setEmail(rs.getString("email"));
				result.setPreference(rs.getString("preference"));
				result.setType(rs.getInt("type"));
				result.setDescription(rs.getString("description"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
		return result;
	}

	@Override
	protected CustomerBean updateCustomer(Connection conn, CustomerBean customer) {
		CustomerBean result = null;
		try {
			PreparedStatement st = conn.prepareStatement(UPDATE_CUSTOMER);
			st.setString(1, customer.getName());
			st.setInt(2, customer.getGender());
			st.setDate(3, new java.sql.Date(customer.getBirthday().getTime()));
			st.setString(4, customer.getCellphone());
			st.setString(5, customer.getEmail());
			st.setString(6, customer.getPreference());
			st.setInt(7, customer.getType());
			st.setString(8, customer.getDescription());
			st.setInt(9, customer.getId());
			if (st.executeUpdate() > 0) result = customer;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
		return result;
	}

	@Override
	protected boolean deleteMutilCustomer(Connection conn, String[] ids) {
		boolean result = false;
		try {
			PreparedStatement st = conn.prepareStatement(DELETE_CUSTOMER_BY_ID);
			for (String id : ids) {
				st.setInt(1, Integer.parseInt(id));
				st.addBatch();
			}
			st.executeBatch();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
		return result;
	}

	@Override
	protected int getNumberOfCustomers(Connection conn) {
		int result = -1;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(GET_NUMBER_OF_CUSTOMERS);
			if (rs.next()) result = rs.getInt("customers");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
		return result;
	}

	@Override
	protected List<CustomerBean> findCustomersByPageId(Connection conn, int id) {
		// TODO Auto-generated method stub
		return null;
	}

}

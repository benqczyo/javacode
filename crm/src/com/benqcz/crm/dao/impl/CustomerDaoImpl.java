package com.benqcz.crm.dao.impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.benqcz.crm.domain.CustomerBean;

public class CustomerDaoImpl extends AbstractCustomerDaoImpl {

	private static final String ADD_CUSTOMER = "INSERT INTO customer (id, name, gender, birthday, cellphone, email, preference, type, description) VALUES (customer_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_CUSTOMER = "UPDATE customer Set name = ?, gender = ?, birthday = ?, cellphone = ?, email = ?, preference = ?, type = ?, description = ? WHERE id = ?";
	private static final String DELETE_CUSTOMER_BY_ID = "DELETE FROM customer WHERE id = ?";
	
	@Override
	protected CustomerBean addCustomer(Connection conn, CustomerBean customer) {
		CustomerBean result = null;
		try {
			PreparedStatement st = conn.prepareStatement(ADD_CUSTOMER, new String[] {"id"});
			st.setString(1, customer.getName());
			st.setInt(2, customer.getGender());
			st.setDate(3, customer.getBirthday());
			st.setString(4, customer.getCellphone());
			st.setString(5, customer.getEmail());
			st.setString(6, customer.getPreference());
			st.setInt(7, customer.getType());
			st.setString(8, customer.getDescription());
			if (st.executeUpdate() > 0) {
				ResultSet rs = st.getGeneratedKeys();
				rs.next();
				customer.setId(rs.getInt(1));
				result = customer;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
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
			throw new RuntimeException(e);
		}
		return result;
	}

	@Override
	protected Map<Integer, CustomerBean> findCustomer(Connection conn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected CustomerBean findCustomerById(Connection conn, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected CustomerBean updateCustomer(Connection conn, CustomerBean customer) {
		// TODO Auto-generated method stub
		return null;
	}

}

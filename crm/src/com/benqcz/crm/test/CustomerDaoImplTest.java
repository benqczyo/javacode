package com.benqcz.crm.test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import com.benqcz.crm.dao.impl.CustomerDaoImpl;
import com.benqcz.crm.domain.CustomerBean;

public class CustomerDaoImplTest {

	@Test
	public void testAddCustomerConnectionCustomerBean() throws ParseException {
		CustomerBean customer = new CustomerBean();
		customer.setName("loxx");
		customer.setGender(1);
		customer.setBirthday(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("1982-08-01").getTime()));
		customer.setCellphone("1234567890");
		customer.setEmail("lox@hotmail.com");
		customer.setPreference("programing");
		customer.setType(3);
		customer.setDescription("common customer");
		CustomerBean result = new CustomerDaoImpl().addCustomer(customer);
		assertNotNull(result);
	}

	@Test
	public void testDeleteCustomerByIdConnectionInt() {
		Assert.assertEquals(true, new CustomerDaoImpl().deleteCustomerById(5));
	}

	@Test
	public void testUpdateCustomerConnectionCustomerBean() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindCustomerConnection() {
		Map<Integer, CustomerBean> customers = new CustomerDaoImpl().findCustomer();
		assertNotNull(customers);
	}

	@Test
	public void testFindCustomerByIdConnectionInt() {
		CustomerBean customer = new CustomerDaoImpl().findCustomerById(4);
		assertNotNull(customer);
		System.out.println(customer);
	}

}

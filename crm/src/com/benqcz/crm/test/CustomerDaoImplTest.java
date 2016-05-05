package com.benqcz.crm.test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import com.benqcz.crm.dao.CustomerDao;
import com.benqcz.crm.dao.impl.CustomerDaoImpl;
import com.benqcz.crm.domain.CustomerBean;

public class CustomerDaoImplTest {

	@Test
	public void testAddCustomerConnectionCustomerBean() throws ParseException {
		CustomerBean customer = new CustomerBean();
		customer.setName("benqcz");
		customer.setGender(1);
		customer.setBirthday(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("1982-08-01").getTime()));
		customer.setCellphone("13705090389");
		customer.setEmail("benqcz@hotmail.com");
		customer.setPreference("programing");
		customer.setType(2);
		customer.setDescription("to be or not to be...");
		CustomerBean result = new CustomerDaoImpl().addCustomer(customer);
		assertNotNull(result);
	}

	@Test
	public void testDeleteCustomerByIdConnectionInt() {
		Assert.assertEquals(true, new CustomerDaoImpl().deleteCustomerById(5));
	}

	@Test
	public void testUpdateCustomerConnectionCustomerBean() {
		CustomerDao dao = new CustomerDaoImpl();
		CustomerBean customer = dao.findCustomerById(2);
		customer.setDescription("suck my dick");
		CustomerBean result = dao.updateCustomer(customer);
		Assert.assertEquals("suck my dick", result.getDescription());
	}

	@Test
	public void testFindCustomerConnection() {
		List<CustomerBean> customers = new CustomerDaoImpl().findCustomer();
		assertNotNull(customers);
	}

	@Test
	public void testFindCustomerByIdConnectionInt() {
		CustomerBean customer = new CustomerDaoImpl().findCustomerById(4);
		assertNotNull(customer);
		System.out.println(customer);
	}
	
	@Test
	public void testGetNumberOfCustomersConnection() {
		int customers = new CustomerDaoImpl().getNumberOfCustomers();
		Assert.assertEquals(5, customers);
	}

	@Test
	public void testFindCustomerByRangeIntInt() {
		List<CustomerBean> customers = new CustomerDaoImpl().findCustomersByRange(0, 2);
		assertNotNull(customers);
		System.out.println(customers);
	}
}

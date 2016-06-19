package com.benqcz.ikanke.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.benqcz.ikanke.proxy.ServiceProxy;
import com.benqcz.ikanke.service.Service;

public class TestCategoryDaoImpl {
	
	Service service = ServiceProxy.getInstance();

	@Test
	public void testGetNumberOfCategories() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddCategory() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelCategoryById() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelCategoryByName() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateCategory() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllCategories() {
		System.out.println(service.findAllCategory());
	}

	@Test
	public void testFindCategoriesByRange() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindCategoryById() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindCategoryByName() {
		fail("Not yet implemented");
	}

}

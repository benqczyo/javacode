package com.benqcz.ikanke.test;

import static org.junit.Assert.fail;
import junit.framework.Assert;

import org.junit.Test;

import com.benqcz.ikanke.domain.impl.BookBean;
import com.benqcz.ikanke.domain.impl.CategoryBean;
import com.benqcz.ikanke.proxy.ServiceProxy;
import com.benqcz.ikanke.service.Service;
import com.benqcz.ikanke.utils.IdUtils;


public class TestServiceImpl {
	
	Service service = ServiceProxy.getInstance();

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
	public void testFindAllCategory() {
		System.out.println(service.findAllCategories());
	}

	@Test
	public void testFindCategoryById() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindCategoryByName() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddBook() {
		BookBean book = new BookBean();
		book.setId(IdUtils.generateId());
		book.setName("The oldman and the sea");
		book.setAuthor("Hemingway");
		book.setPrice(56.00);
		book.setDescription("The Old Man and the Sea is a novel written by the American author Ernest Hemingway in 1951 in Cuba.");
		book.setPic("ppcc.jpg");
		CategoryBean category = new CategoryBean();
		category.setId("a77a97917f984ec884ac98d3611083c9");
		book.setCategory(category);
		Assert.assertTrue(service.addBook(book));
	}

	@Test
	public void testGetPage() {
		fail("Not yet implemented");
	}

}

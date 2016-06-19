package com.benqcz.ikanke.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.benqcz.ikanke.dao.BookDao;
import com.benqcz.ikanke.dao.impl.BookDaoImpl;
import com.benqcz.ikanke.factory.DaoFactory;

public class TestBookDaoImpl {
	
	BookDao dao = DaoFactory.getDaoInstance(BookDaoImpl.class);

	@Test
	public void testGetNumberOfBooks() {
		System.out.println(dao.getNumberOfBooks());
	}

	@Test
	public void testAddBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelBookById() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllBooks() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindBooksByRange() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindBookById() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindBookByName() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindBooksByName() {
		fail("Not yet implemented");
	}

}

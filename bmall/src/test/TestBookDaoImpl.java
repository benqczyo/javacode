package test;

import static org.junit.Assert.*;

import java.util.UUID;

import junit.framework.Assert;

import org.junit.Test;

import dao.BookDao;
import dao.impl.BookDaoImpl;
import domain.impl.BookBean;

public class TestBookDaoImpl {
	
	private BookDao dao = new BookDaoImpl();

	@Test
	public void testAddBook() {
		BookBean book = new BookBean();
		book.setId(UUID.randomUUID().toString());
		book.setName("老人与海");
		book.setAuthor("海明威");
		book.setPicId("8dd87369-ad9c-4c73-ac0c-0206b12e64eb");
		book.setDescription("海明威经典名著");
		book.setCategoryId("8dd87369-ad9c-4c73-ac0c-0206b12e64eb");
		Assert.assertTrue(dao.addBook(book));
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

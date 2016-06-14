package test;

import static org.junit.Assert.*;

import java.util.UUID;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.experimental.categories.Categories;

import utils.IdUtils;

import dao.BookDao;
import dao.impl.BookDaoImpl;
import domain.impl.BookBean;
import domain.impl.CategoryBean;

public class TestBookDaoImpl {
	
	private BookDao dao = new BookDaoImpl();
	
	@Test
	public void testGetNumberOfBooks() {
		System.out.println(dao.getNumberOfBooks());
	}

	@Test
	public void testAddBook() {
		BookBean book = new BookBean();
		book.setId(IdUtils.generateId());
		book.setName("The oldman and the sea");
		book.setAuthor("Hemingway");
		book.setPrice(56.00);
		book.setDescription("The Old Man and the Sea is a novel written by the American author Ernest Hemingway in 1951 in Cuba.");
		book.setPicId(IdUtils.generateId());
		CategoryBean category = new CategoryBean();
		category.setId("320f7651a3544ad980c88d0bbb03eb70");
		book.setCategory(category);
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
		System.out.println(dao.findAllBooks());
	}

	@Test
	public void testFindBookById() {
		System.out.println(dao.findBookById("9a457774ed9f48139fe24831153b21bb"));
	}

	@Test
	public void testFindBookByName() {
		System.out.println(dao.findBookByName("The oldman and the sea"));
	}

	@Test
	public void testFindBooksByName() {
		System.out.println(dao.findBooksByName("The oldman and the sea"));
	}

}

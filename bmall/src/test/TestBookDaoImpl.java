package test;

import static org.junit.Assert.*;

import java.util.UUID;

import junit.framework.Assert;

import org.junit.Test;

import utils.IdUtils;

import dao.BookDao;
import dao.impl.BookDaoImpl;
import domain.impl.BookBean;

public class TestBookDaoImpl {
	
	private BookDao dao = new BookDaoImpl();

	@Test
	public void testAddBook() {
		BookBean book = new BookBean();
		book.setId(IdUtils.generateId());
		book.setName("�����뺣");
		book.setAuthor("������");
		book.setDescription("��������������");
		book.setPicId("1000");
		book.setCategoryId("a60516c0793e4bc485a694bd7358da9c");
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

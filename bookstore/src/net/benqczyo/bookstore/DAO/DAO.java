package net.benqczyo.bookstore.DAO;

import java.sql.SQLException;
import java.util.Map;

import net.benqczyo.bookstore.domain.Book;

public interface DAO {
	public abstract Book findBookById(int id) throws Exception;
	public abstract Map<String, Book> findAllBooks() throws Exception;
}

package net.benqczyo.bookstore.DAO.Impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import net.benqczyo.bookstore.DAO.DAO;
import net.benqczyo.bookstore.domain.Book;
import net.benqczyo.bookstore.util.DBProxy;

public abstract class AbstractDAO implements DAO {
	
	protected abstract Map<String, Book> findAllBooks(Connection conn) throws Exception;

	@Override
	public Map<String, Book> findAllBooks() throws Exception {
		try {
			return findAllBooks(DBProxy.open());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBProxy.close();
		}
	}
	
	protected abstract Book findBookById(Connection conn, int id) throws Exception;

	@Override
	public Book findBookById(int id) throws Exception {
		try {
			return findBookById(DBProxy.open(), id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBProxy.close();
		}
	}
	
}

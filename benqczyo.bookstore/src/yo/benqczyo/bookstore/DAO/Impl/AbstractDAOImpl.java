package yo.benqczyo.bookstore.DAO.Impl;

import java.sql.Connection;
import java.util.Map;

import yo.benqczyo.bookstore.DAO.DAO;
import yo.benqczyo.bookstore.domain.Book;
import yo.benqczyo.bookstore.util.db.DBProxy;

public abstract class AbstractDAOImpl implements DAO {

	@Override
	public Map<Integer, Book> findAllBooks() throws Exception {
		try {
			return findAllBooks(DBProxy.open());
		} catch (Exception e) {
			throw e;
		} finally {
			DBProxy.close();
		}
	}

	@Override
	public Book findBookById(int id) throws Exception {
		try {
			return findBookById(DBProxy.open(), id);
		} catch (Exception e) {
			throw e;
		} finally {
			DBProxy.close();
		}
	}
	
	protected abstract Map<Integer, Book> findAllBooks(Connection conn) throws Exception;
	protected abstract Book findBookById(Connection conn, int id) throws Exception;

}

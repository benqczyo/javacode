package yo.benqczyo.bookstore.DAO.Impl;

import java.sql.Connection;
import java.util.Map;

import yo.benqczyo.bookstore.DAO.BookDAO;
import yo.benqczyo.bookstore.domain.Book;
import yo.benqczyo.bookstore.util.db.DBProxy;

/**
 * 书籍操作DAO虚类
 * 使用findAllBooks(Connection conn), findBookById(Connection conn, int id)重载方法操作数据库
 * @author benqc
 *
 */
public abstract class AbstractBookDAOImpl implements BookDAO {

	/**
	 * 实际调用重载方法findAllBooks(Connection conn)
	 */
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

	/**
	 * 实际调用重载方法findBookById(Connection conn, int id)
	 */
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
	
	/**
	 * 供实际实现类使用的重载方法，实际执行查询所有书籍功能
	 * @param conn 数据库连接
	 * @return Map对象,包含所有书籍
	 * @throws Exception
	 * @see yo.benqczyo.bookstore.domain.Book
	 */
	protected abstract Map<Integer, Book> findAllBooks(Connection conn) throws Exception;
	/**
	 * 供实际实现类使用的重载方法，实际执行查询指定书籍功能
	 * @param conn 数据库连接 
	 * @param id 指定书籍id
	 * @return 指定书籍的Book对象
	 * @throws Exception
	 * @see yo.benqczyo.bookstore.domain.Book
	 */
	protected abstract Book findBookById(Connection conn, int id) throws Exception;

}

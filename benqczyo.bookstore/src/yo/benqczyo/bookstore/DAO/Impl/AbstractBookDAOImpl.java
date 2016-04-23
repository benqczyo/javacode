package yo.benqczyo.bookstore.DAO.Impl;

import java.sql.Connection;
import java.util.Map;

import yo.benqczyo.bookstore.DAO.BookDAO;
import yo.benqczyo.bookstore.domain.Book;
import yo.benqczyo.bookstore.util.db.DBProxy;

/**
 * �鼮����DAO����
 * ʹ��findAllBooks(Connection conn), findBookById(Connection conn, int id)���ط����������ݿ�
 * @author benqc
 *
 */
public abstract class AbstractBookDAOImpl implements BookDAO {

	/**
	 * ʵ�ʵ������ط���findAllBooks(Connection conn)
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
	 * ʵ�ʵ������ط���findBookById(Connection conn, int id)
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
	 * ��ʵ��ʵ����ʹ�õ����ط�����ʵ��ִ�в�ѯ�����鼮����
	 * @param conn ���ݿ�����
	 * @return Map����,���������鼮
	 * @throws Exception
	 * @see yo.benqczyo.bookstore.domain.Book
	 */
	protected abstract Map<Integer, Book> findAllBooks(Connection conn) throws Exception;
	/**
	 * ��ʵ��ʵ����ʹ�õ����ط�����ʵ��ִ�в�ѯָ���鼮����
	 * @param conn ���ݿ����� 
	 * @param id ָ���鼮id
	 * @return ָ���鼮��Book����
	 * @throws Exception
	 * @see yo.benqczyo.bookstore.domain.Book
	 */
	protected abstract Book findBookById(Connection conn, int id) throws Exception;

}

package dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import utils.DBCPUtils;
import utils.IdUtils;

import dao.BookDao;
import domain.impl.BookBean;
import domain.impl.CategoryBean;
import exception.DaoException;

public class BookDaoImpl implements BookDao {

	private static final String GET_NUMBER_OF_BOOKS = "SELECT count(*) FROM book";
	private static final String ADD_BOOK = "INSERT INTO book (id, name, author, price, description, picId, categoryId) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String DEL_BOOK_BY_ID = "DELETE FROM book WHERE id = ?";
	private static final String UPDATE_BOOK = "UPDATE book SET name = ?, author = ?, price = ?, description = ?, picId = ?, categoryId = ? WHERE id = ?";
	private static final String FIND_ALL_BOOKS = "SELECT id, name, author, price, description, picID, categoryId FROM book";
	private static final String FIND_BOOKS_BY_RANGE = "SELECT id, name, author, price, description, picId, categoryId, row_id FROM (SELECT b.*, ROWNUM as row_id FROM (SELECT id, name, author, price, description, picId, categoryId FROM book) b) WHERE row_id BETWEEN ? AND ?";
	private static final String FIND_BOOK_BY_ID = "SELECT id, name, author, price, description, picID, categoryId FROM book WHERE id = ?";
	private static final String FIND_BOOK_BY_NAME = "SELECT id, name, author, price, description, picID, categoryId FROM book WHERE name = ?";
	private static final String FIND_BOOKS_BY_NAME = FIND_BOOK_BY_NAME;

	private QueryRunner qr = new QueryRunner();

	@Override
	public int getNumberOfBooks() {
		try {
			return ((Number) qr.query(DBCPUtils.open(), GET_NUMBER_OF_BOOKS,
					new ScalarHandler(1))).intValue();
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBCPUtils.close();
		}
	}

	@Override
	public boolean addBook(BookBean book) {
		boolean result = false;
		try {
			result = qr.update(DBCPUtils.open(), ADD_BOOK, book.getId(), book
					.getName(), book.getAuthor(), book.getPrice(), book
					.getDescription(), book.getPicId(), book.getCategory()
					.getId()) == 1;
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			DBCPUtils.close();
		}
		return result;
	}

	@Override
	public boolean delBookById(String id) {
		try {
			return qr.update(DBCPUtils.open(), DEL_BOOK_BY_ID, id) == 1;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBCPUtils.close();
		}
	}

	@Override
	public boolean updateBook(BookBean book) {
		boolean result = false;
		try {
			return qr.update(DBCPUtils.open(), UPDATE_BOOK, book.getName(),
					book.getAuthor(), book.getPrice(), book.getDescription(),
					book.getPicId(), book.getCategory().getId()) == 1;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBCPUtils.close();
		}
	}

	@Override
	public List<BookBean> findAllBooks() {
		List<BookBean> result = null;
		try {
			return qr.query(DBCPUtils.open(), FIND_ALL_BOOKS,
					new BeanListHandler<BookBean>(BookBean.class));
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBCPUtils.close();
		}
	}

	@Override
	public List<BookBean> findBooksByRange(int startRecordId, int endRecordId) {
		List<BookBean> result = null;
		try {
			result = qr.query(DBCPUtils.open(), FIND_BOOKS_BY_RANGE,
					new BeanListHandler<BookBean>(BookBean.class),
					startRecordId, endRecordId);
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBCPUtils.close();
		}
		return result;
	}

	@Override
	public BookBean findBookById(String id) {
		BookBean result = null;
		try {
			return qr.query(DBCPUtils.open(), FIND_BOOK_BY_ID,
					new BeanHandler<BookBean>(BookBean.class), id);
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBCPUtils.close();
		}
	}

	@Override
	public BookBean findBookByName(String name) {
		BookBean result = null;
		try {
			return qr.query(DBCPUtils.open(), FIND_BOOK_BY_NAME,
					new BeanHandler<BookBean>(BookBean.class), name);
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBCPUtils.close();
		}
	}

	@Override
	public List<BookBean> findBooksByName(String name) {
		List<BookBean> result = null;
		try {
			return qr.query(DBCPUtils.open(), FIND_BOOKS_BY_NAME,
					new BeanListHandler<BookBean>(BookBean.class), name);
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBCPUtils.close();
		}
	}

}

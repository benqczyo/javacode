package com.benqcz.ikanke.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.benqcz.ikanke.dao.AbstractDao;
import com.benqcz.ikanke.dao.BookDao;
import com.benqcz.ikanke.domain.impl.BookBean;
import com.benqcz.ikanke.exception.DaoException;
import com.benqcz.ikanke.utils.DBCPUtils;

public class BookDaoImpl extends AbstractDao implements BookDao {

	private static final String GET_NUMBER_OF_BOOKS = "SELECT count(*) FROM book";
	private static final String GET_NUMBER_OF_BOOKS_BY_CATEGORY_ID = "SELECT count(*) FROM book WHERE categoryId = ?";
	private static final String ADD_BOOK = "INSERT INTO book (id, name, author, price, description, pic, categoryId) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String DEL_BOOK_BY_ID = "DELETE FROM book WHERE id = ?";
	private static final String UPDATE_BOOK = "UPDATE book SET name = ?, author = ?, price = ?, description = ?, pic = ?, categoryId = ? WHERE id = ?";
	private static final String FIND_ALL_BOOKS = "SELECT id, name, author, price, description, pic, categoryId FROM book";
	private static final String FIND_BOOKS_BY_RANGE = "SELECT id, name, author, price, description, pic, categoryId, row_id FROM (SELECT b.*, ROWNUM as row_id FROM (SELECT id, name, author, price, description, pic, categoryId FROM book) b) WHERE row_id BETWEEN ? AND ?";
	private static final String FIND_BOOKS_BY_RANGE_WITH_CATEGORY_ID = "SELECT id, name, author, price, description, pic, categoryId, row_id FROM (SELECT b.*, ROWNUM as row_id FROM (SELECT id, name, author, price, description, pic, categoryId FROM book) b) WHERE categoryId = ? AND row_id BETWEEN ? AND ?";
	private static final String FIND_BOOK_BY_ID = "SELECT id, name, author, price, description, pic, categoryId FROM book WHERE id = ?";
	private static final String FIND_BOOK_BY_NAME = "SELECT id, name, author, price, description, pic, categoryId FROM book WHERE name = ?";
	private static final String FIND_BOOKS_BY_NAME = FIND_BOOK_BY_NAME;
	
	@Override
	public int getNumberOfBooks() {
		try {
			return ((Number) qr.query(DBCPUtils.open(), GET_NUMBER_OF_BOOKS,
					new ScalarHandler(1))).intValue();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}
	
	@Override
	public int getNumberOfBooksByCategoryId(String categoryId) {
		try {
			return ((Number) qr.query(DBCPUtils.open(), GET_NUMBER_OF_BOOKS_BY_CATEGORY_ID, new ScalarHandler(1), categoryId)).intValue();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public boolean addBook(BookBean book) {
		try {
			return qr.update(DBCPUtils.open(), ADD_BOOK, book.getId(), book
					.getName(), book.getAuthor(), book.getPrice(), book
					.getDescription(), book.getPic(), book.getCategory()
					.getId()) == 1;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public boolean delBookById(String id) {
		try {
			return qr.update(DBCPUtils.open(), DEL_BOOK_BY_ID, id) == 1;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public boolean updateBook(BookBean book) {
		try {
			return qr.update(DBCPUtils.open(), UPDATE_BOOK, book.getName(),
					book.getAuthor(), book.getPrice(), book.getDescription(),
					book.getPic(), book.getCategory().getId()) == 1;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<BookBean> findAllBooks() {
		try {
			return qr.query(DBCPUtils.open(), FIND_ALL_BOOKS,
					new BeanListHandler<BookBean>(BookBean.class));
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<BookBean> findBooksByRange(int startRecordId, int endRecordId) {
		try {
			return qr.query(DBCPUtils.open(), FIND_BOOKS_BY_RANGE,
					new BeanListHandler<BookBean>(BookBean.class),
					startRecordId, endRecordId);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public BookBean findBookById(String id) {
		try {
			return qr.query(DBCPUtils.open(), FIND_BOOK_BY_ID,
					new BeanHandler<BookBean>(BookBean.class), id);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public BookBean findBookByName(String name) {
		try {
			return qr.query(DBCPUtils.open(), FIND_BOOK_BY_NAME,
					new BeanHandler<BookBean>(BookBean.class), name);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<BookBean> findBooksByName(String name) {
		try {
			return qr.query(DBCPUtils.open(), FIND_BOOKS_BY_NAME,
					new BeanListHandler<BookBean>(BookBean.class), name);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<BookBean> findBooksByRangeWithCategoryId(int startRecordId,
			int endRecordId, String categoryId) {
		try {
			return qr.query(DBCPUtils.open(), FIND_BOOKS_BY_RANGE_WITH_CATEGORY_ID,
					new BeanListHandler<BookBean>(BookBean.class),
					categoryId, startRecordId, endRecordId);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

}

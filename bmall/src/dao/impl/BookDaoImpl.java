package dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import utils.DBCPUtils;
import utils.IdUtils;

import dao.BookDao;
import domain.impl.BookBean;
import exception.DaoException;

public class BookDaoImpl implements BookDao {

	private final String ADD_BOOK = "INSERT INTO book (id, name, author, description, picId, categoryId) VALUES (?, ?, ?, ?, ?, ?)";

	private QueryRunner qr = new QueryRunner();
	
	@Override
	public boolean addBook(BookBean book) {
		boolean result = false;
		try {
			result = qr.update(DBCPUtils.open(), ADD_BOOK, book.getId(),
					book.getName(), book.getAuthor(), book.getDescription(),
					book.getPicId(), book.getCategoryId()) == 1;
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			DBCPUtils.close();
		}
		return result;
	}

	@Override
	public boolean delBookById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBook(BookBean book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<BookBean> findAllBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookBean findBookById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookBean findBookByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookBean> findBooksByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}

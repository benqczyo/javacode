package yo.benqczyo.bookstore.service.Impl;

import java.util.Map;

import yo.benqczyo.bookstore.DAO.BookDAO;
import yo.benqczyo.bookstore.DAO.Impl.BookDAOImpl;
import yo.benqczyo.bookstore.domain.Book;
import yo.benqczyo.bookstore.service.BookService;

public class BookServiceImpl implements BookService {

	private BookDAO dao = new BookDAOImpl();
	
	@Override
	public Map<Integer, Book> findAllBooks() throws Exception {
		return dao.findAllBooks();
	}

	@Override
	public Book findBookById(int id) throws Exception {
		return dao.findBookById(id);
	}

}

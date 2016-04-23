package yo.benqczyo.bookstore.DAO;

import java.util.Map;

import yo.benqczyo.bookstore.domain.Book;

public interface DAO {
	public abstract Map<Integer, Book> findAllBooks() throws Exception;
	public abstract Book findBookById(int id) throws Exception;
}

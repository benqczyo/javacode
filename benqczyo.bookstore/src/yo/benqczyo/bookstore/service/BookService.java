package yo.benqczyo.bookstore.service;

import java.util.Map;

import yo.benqczyo.bookstore.domain.Book;

public interface BookService {
	public abstract Map<Integer, Book> findAllBooks() throws Exception;
	public abstract Book findBookById(int id) throws Exception;
}

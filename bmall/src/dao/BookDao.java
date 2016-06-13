package dao;

import java.util.List;

import domain.impl.BookBean;

public interface BookDao {
	boolean addBook(BookBean book);
	boolean delBookById(String id);
	boolean updateBook(BookBean book);
	List<BookBean> findAllBooks();
	BookBean findBookById(String id);
	BookBean findBookByName(String name);
	List<BookBean> findBooksByName(String name);
}

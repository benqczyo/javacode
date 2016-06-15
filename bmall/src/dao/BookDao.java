package dao;

import java.util.List;

import domain.impl.BookBean;
import domain.impl.CategoryBean;

public interface BookDao {
	int getNumberOfBooks();
	boolean addBook(BookBean book);
	boolean delBookById(String id);
	boolean updateBook(BookBean book);
	List<BookBean> findAllBooks();
	List<BookBean> findBooksByRange(int startRecordId, int endRecordId);
	BookBean findBookById(String id);
	BookBean findBookByName(String name);
	List<BookBean> findBooksByName(String name);
}

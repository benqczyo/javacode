package com.benqcz.ikanke.dao;

import java.util.List;

import com.benqcz.ikanke.domain.impl.BookBean;

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
	List<BookBean> findBooksByRangeWithCategoryId(int startRecordId,
			int endRecordId, String categoryId);
	int getNumberOfBooksByCategoryId(String categoryId);
}

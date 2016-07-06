package yo.benqczyo.bookstore.service;

import java.util.Map;

import yo.benqczyo.bookstore.domain.Book;
import yo.benqczyo.bookstore.domain.Cart;
import yo.benqczyo.bookstore.domain.CartItem;

public interface BookService {
	public abstract Map<Integer, Book> findAllBooks() throws Exception;
	public abstract Book findBookById(int i) throws Exception;
	public abstract CartItem add2Cart(Cart cart, Book book);
	public abstract boolean change(Cart cart, String id, String newValue);
	public abstract void delete(Cart cart, String id) throws Exception;
}

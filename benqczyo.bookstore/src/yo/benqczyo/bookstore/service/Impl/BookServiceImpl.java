package yo.benqczyo.bookstore.service.Impl;

import java.util.Map;

import yo.benqczyo.bookstore.DAO.BookDAO;
import yo.benqczyo.bookstore.DAO.Impl.BookDAOImpl;
import yo.benqczyo.bookstore.domain.Book;
import yo.benqczyo.bookstore.domain.Cart;
import yo.benqczyo.bookstore.domain.CartItem;
import yo.benqczyo.bookstore.service.BookService;

public class BookServiceImpl implements BookService {

	private BookDAO dao = new BookDAOImpl();
	
	private boolean isValidateDigital(String value) {
		return value.matches("([1-9][0-9]*)||0");
	}
	
	@Override
	public Map<Integer, Book> findAllBooks() throws Exception {
		return dao.findAllBooks();
	}

	@Override
	public Book findBookById(int id) throws Exception {
		return dao.findBookById(id);
	}

	@Override
	public CartItem add2Cart(Cart cart, Book book) {
		return cart.add2Cart(book);
	}

	@Override
	public boolean change(Cart cart, String id, String newValue) {
		boolean result = false;
		Map<String, CartItem> items = cart.getItems();
		CartItem item = items.get(id);
		if (item != null && isValidateDigital(newValue)) {
			int value = Integer.parseInt(newValue);
			if (value == 0) {
				items.remove(id);
			} else {
				item.setCount(value);
			}
			result = true;
		}
		return result;
	}

}

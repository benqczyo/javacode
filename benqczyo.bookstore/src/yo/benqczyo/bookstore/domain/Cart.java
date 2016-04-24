package yo.benqczyo.bookstore.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Cart {

	private Map<Book, CartItem> items = new HashMap<Book, CartItem>();

	public int getCount() {
		int result = 0;
		for (Entry<Book, CartItem> item : items.entrySet()) {
			result += item.getValue().getCount();
		}
		return result;
	}

	public double getPrice() {
		double result = 0;
		for (Entry<Book, CartItem> item : items.entrySet()) {
			result += item.getValue().getPrice();
		}
		return result;
	}
	
	public Map<Book, CartItem> getItems() {
		return items;
	}

	public CartItem add2Cart(Book book) {
		CartItem result = items.get(book);
		if (result == null) {
			items.put(book, new CartItem(book));
		} else {
			result.setCount(result.getCount() + 1);
		}
		return items.get(book);
	}

}

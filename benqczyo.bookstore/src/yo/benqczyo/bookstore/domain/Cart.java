package yo.benqczyo.bookstore.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Cart {

	private Map<String, CartItem> items = new HashMap<String, CartItem>();

	public int getCount() {
		int result = 0;
		for (Entry<String, CartItem> item : items.entrySet()) {
			result += item.getValue().getCount();
		}
		return result;
	}

	public double getPrice() {
		double result = 0;
		for (Entry<String, CartItem> item : items.entrySet()) {
			result += item.getValue().getPrice();
		}
		return result;
	}
	
	public Map<String, CartItem> getItems() {
		return items;
	}

	public CartItem add2Cart(Book book) {
		CartItem result = items.get(String.valueOf(book.getId()));
		if (result == null) {
			items.put(String.valueOf(book.getId()), new CartItem(book));
		} else {
			result.setCount(result.getCount() + 1);
		}
		return items.get(book);
	}

}

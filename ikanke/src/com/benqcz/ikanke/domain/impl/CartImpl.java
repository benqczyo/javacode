package com.benqcz.ikanke.domain.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.benqcz.ikanke.domain.Cart;
import com.benqcz.ikanke.utils.IdUtils;

public class CartImpl implements Cart {
	
	private Map<String, CartItem> items = new HashMap<String, CartItem>();
	private int numberOfBooks;
	private float totalPrice;
	
	public int getNumberOfBooks() {
		int result = 0;
		for (Entry<String, CartItem> item : items.entrySet())
			result += item.getValue().getNumberOfBooks();
		return result;
	}

	public float getTotalPrice() {
		float result = 0;
		for (Entry<String, CartItem> item : items.entrySet())
			result += item.getValue().getTotalPrice();
		return result;
	}

	public Map<String, CartItem> getItems() {
		return items;
	}
	
	@Override
	public void putIntoCart(BookBean book) {
		if (!items.containsKey(book.getId())) {
			CartItem item = new CartItem();
			item.setId(book.getId());
			item.setBook(book);
			item.setNumberOfBooks(1);
			System.out.println(item);
			items.put(item.getId(), item);
		} else {
			CartItem item = items.get(book.getId());
			item.setNumberOfBooks(item.getNumberOfBooks() + 1);
		}
	}

	@Override
	public void delItem(BookBean book) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateItem(CartItem item) {
		// TODO Auto-generated method stub

	}

	@Override
	public CartItem findItem(String bookName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		return String.format(
				"Cart [items=%s, numberOfBooks=%s, totalPrice=%s]", items,
				this.getNumberOfBooks(), this.getTotalPrice());
	}

}

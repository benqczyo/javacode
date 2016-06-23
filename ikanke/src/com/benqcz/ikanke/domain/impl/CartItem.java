package com.benqcz.ikanke.domain.impl;

import java.io.Serializable;

public class CartItem implements Serializable {
	
	private String id;
	private BookBean book;
	private int numberOfBooks;
	private float totalPrice;
	
	public CartItem() {}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public BookBean getBook() {
		return book;
	}

	public void setBook(BookBean book) {
		this.book = book;
	}

	public int getNumberOfBooks() {
		return numberOfBooks;
	}

	public void setNumberOfBooks(int numberOfBooks) {
		this.numberOfBooks = numberOfBooks;
	}

	public float getTotalPrice() {
		this.totalPrice = (float) (this.numberOfBooks * this.book.getPrice());
		return totalPrice;
	}

	@Override
	public String toString() {
		return String.format(
				"CartItem [book=%s, numberOfBooks=%s, totalPrice=%s]", book,
				numberOfBooks, totalPrice);
	}
	
}

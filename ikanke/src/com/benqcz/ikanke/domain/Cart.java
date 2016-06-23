package com.benqcz.ikanke.domain;

import java.io.Serializable;

import com.benqcz.ikanke.domain.impl.CartItem;

public interface Cart extends Serializable {
	void putIntoCart(CartItem item);
	void delItem(CartItem item);
	void updateItem(CartItem item);
	CartItem findItem(String bookName);
}

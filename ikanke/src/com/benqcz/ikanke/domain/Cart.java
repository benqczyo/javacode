package com.benqcz.ikanke.domain;

import java.io.Serializable;

import com.benqcz.ikanke.domain.impl.BookBean;
import com.benqcz.ikanke.domain.impl.CartItem;

public interface Cart extends Serializable {
	void putIntoCart(BookBean book);
	void delItem(BookBean book);
	void updateItem(CartItem item);
	CartItem findItem(String bookName);
}

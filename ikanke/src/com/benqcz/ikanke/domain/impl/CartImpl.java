package com.benqcz.ikanke.domain.impl;

import java.util.HashMap;
import java.util.Map;

import com.benqcz.ikanke.domain.Cart;

public class CartImpl implements Cart {
	
	private Map<String, CartItem> items = new HashMap<String, CartItem>();
	private int numberOfItems;
	private double totalPrice;
	
	public int getNumberOfItems() {
		return items.size();
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public Map<String, CartItem> getItems() {
		return items;
	}
	
	@Override
	public void putIntoCart(CartItem item) {
		//û����Ŀ��Ŀ��ӽ�ȥ
		//������Ŀ���鱾������
		if (!items.containsValue(item))
			items.put(item.getId(), item);
		
	}

	@Override
	public void delItem(CartItem item) {
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
				"Cart [items=%s, numberOfItems=%s, totalPrice=%s]", items,
				numberOfItems, totalPrice);
	}

}

package db;

import java.util.ArrayList;
import java.util.List;

public class ProductDB {
	
	protected static List<Product> products;
	
	static {
		products = new ArrayList<Product>();
		products.add(new Product("努比亚Z9", "努比亚(nubia)Z9 全黑 极速版 移动联通电信4G 智能手机 (电信G4+)", 2199.00, "11.jpg"));
		products.add(new Product("荣耀6 Plus", "荣耀 6 Plus (PE-TL10) 3GB+32GB内存版 晨曦金 移动联通双4G手机", 1599.00, "22.jpg"));
		products.add(new Product("奇酷（QIKU）", "奇酷（QIKU）旗舰版玫瑰金移动联通电信4G手机双卡双待", 3299.00, "33.jpg"));
		products.add(new Product("奇酷（QIKU）", "奇酷（QIKU）旗舰版白色移动联通电信4G手机双卡双待", 4299.00, "44.jpg"));
	}
	
	public static List<Product> findAll() {
		return new ArrayList(products);
	}

	public static void main(String[] args) {
		System.out.println(ProductDB.findAll());
	}
}

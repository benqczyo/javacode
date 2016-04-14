package db;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class BookDB {
	
	private static HashMap<String, Book> books;
	
	static {
		books = new LinkedHashMap<String, Book>();
		books.put("1", new Book("1", "葵花宝典", "无名氏", "欲练此功必先自宫", 5000.00));
		books.put("2", new Book("2", "九阴真经", "黄裳", "本土道家神功", 8000.00));
		books.put("3", new Book("3", "九阳神功", "达摩", "张无忌现身说法", 8000.00));
		books.put("4", new Book("4", "辟邪剑谱", "林平之", "欲练此功必先自宫", 5000.00));
		books.put("5", new Book("5", "鳞波微步", "段誉", "比闪电侠还快", 2000.00));
	}
	
	public static Book findBookById(String id) {
		return books.get(id);
	}
	
	public static HashMap<String, Book> findAllBooks() {
		return books;
	}
	
}

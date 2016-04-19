package db;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class BookDB {
	
	private static HashMap<String, Book> books;
	
	static {
		books = new LinkedHashMap<String, Book>();
		books.put("1", new Book("1", "��������", "������", "�����˹������Թ�", 5000.00));
		books.put("2", new Book("2", "�����澭", "����", "����������", 8000.00));
		books.put("3", new Book("3", "������", "��Ħ", "���޼�����˵��", 8000.00));
		books.put("4", new Book("4", "��а����", "��ƽ֮", "�����˹������Թ�", 5000.00));
		books.put("5", new Book("5", "�۲�΢��", "����", "������������", 2000.00));
	}
	
	public static Book findBookById(String id) {
		return books.get(id);
	}
	
	public static HashMap<String, Book> findAllBooks() {
		return books;
	}
	
}

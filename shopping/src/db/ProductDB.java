package db;

import java.util.ArrayList;
import java.util.List;

public class ProductDB {
	
	protected static List<Product> products;
	
	static {
		products = new ArrayList<Product>();
		products.add(new Product("Ŭ����Z9", "Ŭ����(nubia)Z9 ȫ�� ���ٰ� �ƶ���ͨ����4G �����ֻ� (����G4+)", 2199.00, "11.jpg"));
		products.add(new Product("��ҫ6 Plus", "��ҫ 6 Plus (PE-TL10) 3GB+32GB�ڴ�� ���ؽ� �ƶ���ͨ˫4G�ֻ�", 1599.00, "22.jpg"));
		products.add(new Product("��ᣨQIKU��", "��ᣨQIKU���콢��õ����ƶ���ͨ����4G�ֻ�˫��˫��", 3299.00, "33.jpg"));
		products.add(new Product("��ᣨQIKU��", "��ᣨQIKU���콢���ɫ�ƶ���ͨ����4G�ֻ�˫��˫��", 4299.00, "44.jpg"));
	}
	
	public static List<Product> findAll() {
		return new ArrayList(products);
	}

	public static void main(String[] args) {
		System.out.println(ProductDB.findAll());
	}
}

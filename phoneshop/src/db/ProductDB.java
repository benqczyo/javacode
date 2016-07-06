package db;

import java.util.ArrayList;
import java.util.List;

public class ProductDB {
	
	protected static List<Product> products;
	
	static {
		products = new ArrayList<Product>();
		products.add(new Product(1, "Ŭ����Z9", "Ŭ����(nubia)Z9 ȫ�� ���ٰ� �ƶ���ͨ����4G �����ֻ� (����G4+)", 2199.00, "11.jpg"));
		products.add(new Product(2, "��ҫ6 Plus", "��ҫ 6 Plus (PE-TL10) 3GB+32GB�ڴ�� ���ؽ� �ƶ���ͨ˫4G�ֻ�", 1599.00, "22.jpg"));
		products.add(new Product(3, "��ᣨQIKU��", "��ᣨQIKU���콢��õ����ƶ���ͨ����4G�ֻ�˫��˫��", 3299.00, "33.jpg"));
		products.add(new Product(4, "���� Galaxy Note4 (N9108V)", "���� Galaxy Note4 (N9108V) ��Ӱ�� �ƶ�4G�ֻ�", 4299.00, "44.jpg"));
		products.add(new Product(5, "Ŭ����Z9", "Ŭ����(nubia)Z9 ȫ�� ���ٰ� �ƶ���ͨ����4G �����ֻ� (����G4+)", 2199.00, "11.jpg"));
		products.add(new Product(6, "��ҫ6 Plus", "��ҫ 6 Plus (PE-TL10) 3GB+32GB�ڴ�� ���ؽ� �ƶ���ͨ˫4G�ֻ�", 1599.00, "22.jpg"));
		products.add(new Product(7, "��ᣨQIKU��", "��ᣨQIKU���콢��õ����ƶ���ͨ����4G�ֻ�˫��˫��", 3299.00, "33.jpg"));
		products.add(new Product(8, "���� Galaxy Note4 (N9108V)", "���� Galaxy Note4 (N9108V) ��Ӱ�� �ƶ�4G�ֻ�", 4299.00, "44.jpg"));
	}
	
	public static List<Product> findAll() {
		return new ArrayList(products);
	}
	
	public static Product findProductById(int id) {
		return --id >= 0 && id <= products.size() ? new Product(products.get(id)) : null;
	}

	public static int getSize() {
		return products.size();
	}
}

package yo.benqczyo.bookstore.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 书籍JavaBean对象 id 书籍id isbn 书籍isbn title 书籍名字 pub 书籍出版日期 author 书籍作者
 * description 书籍简介
 * 
 * @author benqcz
 */
public class Book implements Serializable {

	private int id;
	private String isbn;
	private String title;
	private Date pub;
	private String author;
	private String description;
	private double price;

	public Book() {

	}

	public Book(int id, String isbn, String title, Date pub, String author,
			String description, double price) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.pub = pub;
		this.author = author;
		this.description = description;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPub() {
		return pub;
	}

	public void setPub(Date pub) {
		this.pub = pub;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book [author=" + author + ", description=" + description
				+ ", id=" + id + ", isbn=" + isbn + ", price=" + price
				+ ", pub=" + pub + ", title=" + title + "]";
	}

}

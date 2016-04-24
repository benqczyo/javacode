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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((pub == null) ? 0 : pub.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (Double.doubleToLongBits(price) != Double
				.doubleToLongBits(other.price))
			return false;
		if (pub == null) {
			if (other.pub != null)
				return false;
		} else if (!pub.equals(other.pub))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [author=" + author + ", description=" + description
				+ ", id=" + id + ", isbn=" + isbn + ", price=" + price
				+ ", pub=" + pub + ", title=" + title + "]";
	}

}

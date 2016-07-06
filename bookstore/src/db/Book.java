package db;

public class Book {
	
	private String id;
	private String name;
	private String author;
	private String description;
	private double price;
	
	public Book() {}
	
	public Book(String id, String name, String author, String description, double price) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.description = description;
		this.price = price;
	}
	
	public Book(Book book) {
		this.id = book.id;
		this.name = book.name;
		this.author = book.author;
		this.description = book.description;
		this.price = this.price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
				+ ", id=" + id + ", name=" + name + ", price=" + price + "]";
	}
	
}

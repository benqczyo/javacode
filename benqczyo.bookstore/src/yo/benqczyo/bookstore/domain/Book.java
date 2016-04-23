package yo.benqczyo.bookstore.domain;

import java.io.Serializable;

public class Book implements Serializable {

	private int id;
	private String isbn;
	private String title;
	private String author;
	private String description;

	public Book() {

	}

	public Book(int id, String isbn, String title, String author, String description) {
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.description = description;
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

	@Override
	public String toString() {
		return String.format(
				"Book [author=%s, description=%s, id=%s, isbn=%s, title=%s]",
				author, description, id, isbn, title);
	}

}

package domain.impl;

import domain.Bean;

public class BookBean implements Bean {
	
	private String id;
	private String name;
	private String author;
	private String picId;
	private String description;
	private String categoryId;
	
	public BookBean() {
		
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

	public String getPicId() {
		return picId;
	}

	public void setPicId(String picId) {
		this.picId = picId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return String
				.format("BookBean [id=%s, name=%s, author=%s, picId=%s, description=%s, categoryId=%s]",
						id, name, author, picId, description, categoryId);
	}
	
}

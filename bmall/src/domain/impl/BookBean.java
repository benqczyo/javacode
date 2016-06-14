package domain.impl;

import domain.Bean;

public class BookBean implements Bean {
	
	private String id; 
	private String name;
	private String author;
	private double price;
	private String description;
	private String picId;
	private String categoryId;
	private CategoryBean category;
	
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicId() {
		return picId;
	}
	
	public void setPicId(String picId) {
		this.picId = picId;
	}
	
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public CategoryBean getCategory() {
		return category;
	}

	public void setCategory(CategoryBean category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return String
				.format("BookBean [id=%s, name=%s, author=%s, price=%s, description=%s, picId=%s, categoryId=%s, category=%s]",
						id, name, author, price, description, picId,
						categoryId, category);
	}

}

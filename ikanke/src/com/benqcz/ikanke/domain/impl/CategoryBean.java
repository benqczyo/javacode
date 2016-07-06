package com.benqcz.ikanke.domain.impl;

import java.util.List;

import com.benqcz.ikanke.domain.Bean;

public class CategoryBean implements Bean {

	private String id;
	private String name;
	private String description;
	private List<BookBean> books;
	
	public CategoryBean() {
		
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<BookBean> getBooks() {
		return books;
	}

	public void setBooks(List<BookBean> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return String.format("CatetoryBean [id=%s, name=%s, description=%s]",
				id, name, description);
	}
	
}

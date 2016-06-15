package formbean.impl;

import java.util.List;

import domain.impl.CategoryBean;

public class AddBookFormBean extends AbstractFormBean {

	private String name;
	private String author;
	private String price;
	private String description;
	private String picId;
	private String categoryId;
	private List<CategoryBean> categories;

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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
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

	public List<CategoryBean> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryBean> categories) {
		this.categories = categories;
	}

	@Override
	public boolean isValidated() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		return String
				.format("AddBookFormBean [name=%s, author=%s, price=%s, picId=%s, description=%s, categoryId=%s, categories=%s]",
						name, author, price, picId, description, categoryId,
						categories);
	}

}

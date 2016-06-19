package com.benqcz.ikanke.web.formbean.impl;

import java.util.List;

import com.benqcz.ikanke.domain.impl.CategoryBean;

public class AddBookFormBean extends AbstractFormBean {

	private String name;
	private String author;
	private String price;
	private String description;
	private String pic;
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
	
	public String getPic() {
		return pic;
	}
	
	public void setPic(String pic) {
		this.pic = pic;
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
		if (name == null || name.trim().isEmpty()) messages.put("name", "请输入书名");
		if (author == null || author.trim().isEmpty()) messages.put("author", "请输入作者");
		if (price == null || price.trim().isEmpty()) messages.put("price", "请输入价格");
		if (description == null || description.trim().isEmpty()) messages.put("description", "请输入描述");
		if (pic == null || pic.trim().isEmpty()) messages.put("pic", "请选择要上传的图片文件");
		return super.isValidated();
	}

	@Override
	public String toString() {
		return String
				.format("AddBookFormBean [name=%s, author=%s, price=%s, picId=%s, description=%s, categoryId=%s, categories=%s]",
						name, author, price, pic, description, categoryId,
						categories);
	}

}

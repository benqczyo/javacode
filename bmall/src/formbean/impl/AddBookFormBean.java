package formbean.impl;

import java.util.List;

import domain.impl.CategoryBean;

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
		if (name == null || name.trim().isEmpty()) messages.put("name", "����������");
		if (author == null || author.trim().isEmpty()) messages.put("author", "����������");
		if (price == null || price.trim().isEmpty()) messages.put("price", "������۸�");
		if (description == null || description.trim().isEmpty()) messages.put("description", "����������");
		if (pic == null || pic.trim().isEmpty()) messages.put("pic", "��ѡ��Ҫ�ϴ���ͼƬ�ļ�");
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

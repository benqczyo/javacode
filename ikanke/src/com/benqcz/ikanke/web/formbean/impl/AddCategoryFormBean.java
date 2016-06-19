package com.benqcz.ikanke.web.formbean.impl;


public class AddCategoryFormBean extends AbstractFormBean {
	
	private String id;
	private String name;
	private String description;
	
	public AddCategoryFormBean() {}

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
	
	@Override
	public boolean isValidated() {
		if (name == null || name.trim().isEmpty()) messages.put("name", "请输入分类名称");
		if (description == null || description.trim().isEmpty()) messages.put("description", "请输入分类描述");
		return super.isValidated();
	}

	@Override
	public String toString() {
		return String.format(
				"AddCategoryFormBean [id=%s, name=%s, description=%s]", id,
				name, description);
	}
	
}

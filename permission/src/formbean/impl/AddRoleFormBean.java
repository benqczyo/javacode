package formbean.impl;

import java.util.Map;

import formbean.FormBean;

public class AddRoleFormBean extends AbstractFormBean {

	private String id;
	private String name;
	private String description;
	
	public AddRoleFormBean() {}

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
	public boolean validate() {
		if (name == null || name.trim().equals("")) {
			messages.put("name", "«Î ‰»ÎΩ«…´√˚≥∆");
		}

		if (description == null || description.trim().equals("")) {
			messages.put("description", "«Î ‰»Î√Ë ˆ");
		}

		return messages.isEmpty();
	}

	@Override
	public String toString() {
		return "AddRoleFormBean [description=" + description + ", id=" + id
		+ ", name=" + name + "]";
	}
	
}

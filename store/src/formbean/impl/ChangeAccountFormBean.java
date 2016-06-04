package formbean.impl;

import java.util.Map;

import formbean.FormBean;

public class ChangeAccountFormBean extends AbstractFormBean {


	private String id;
	private String name;
	private String oldPassword;
	private String newPassword;

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public ChangeAccountFormBean() {
	}

	public String getName() {
		return name;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
	public boolean validate() {
		if (oldPassword == null || oldPassword.trim().equals("")) 
			messages.put("oldPassword", "旧密码不能为空");
		if (newPassword == null || newPassword.trim().equals("")) 
			messages.put("newPassword", "新密码不能为空");
		return messages.isEmpty();
	}

	@Override
	public String toString() {
		return "ChangeAccountFormBean [id=" + id + ", name=" + name
				+ ", newPassword=" + newPassword + ", oldPassword="
				+ oldPassword + "]";
	}

}

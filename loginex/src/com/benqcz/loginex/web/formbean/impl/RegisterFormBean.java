package com.benqcz.loginex.web.formbean.impl;

public class RegisterFormBean extends AbstractFormBeanImpl {

	private String name;
	private String password;
	private String email;

	public RegisterFormBean() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean validate() {
		if (name == null || name.trim().equals(""))
			messages.put("name", "«Î ‰»Î–’√˚");
		if (password == null || password.trim().equals(""))
			messages.put("password", "«Î ‰»Î√‹¬Î");
		if (email == null || email.trim().equals(""))
			messages.put("email", "«Î ‰»Î” œ‰");
		return messages.isEmpty();
	}

	@Override
	public String toString() {
		return String.format(
				"RegisterFormBean [email=%s, name=%s, password=%s]", email,
				name, password);
	}

}

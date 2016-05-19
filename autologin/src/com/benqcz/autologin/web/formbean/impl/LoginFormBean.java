package com.benqcz.autologin.web.formbean.impl;

public class LoginFormBean extends AbstractFormBeanImpl {
	
	private String name;
	private String password;
	private String auto;

	public LoginFormBean() {}

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
	
	public String getAuto() {
		return auto;
	}

	public void setAuto(String auto) {
		this.auto = auto;
	}

	public boolean validate() {
		if (name == null || name.trim().equals("")) messages.put("name", "����������");
		if (password == null || password.trim().equals("")) messages.put("password", "����������");
		return messages.isEmpty() ;
	}

	@Override
	public String toString() {
		return "LoginFormBean [name=" + name + ", password=" + password + "]";
	}
	
	
	
}

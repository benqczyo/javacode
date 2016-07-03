package com.benqcz.ikanke.web.formbean.impl;

public class RegisterFormBean extends AbstractFormBean {

	private String name;
	private String password;
	private String cellphone;
	private String email;
	private String address;
	
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

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public boolean isValidated() {
		if (name == null || name.trim().isEmpty()) {
			messages.put("name", "请输入账户名");
		}
		if (password == null || password.trim().isEmpty()) {
			messages.put("password", "请输入密码");
		}
		if (cellphone == null || cellphone.trim().isEmpty()) {
			messages.put("cellphone", "请输入手机号码");
		}
		if (email == null || email.trim().isEmpty()) {
			messages.put("email", "请输入电邮地址");
		}
		if (address == null || address.trim().isEmpty()) {
			messages.put("address", "请输入收件地址");
		}
		return super.isValidated();
	}

	@Override
	public String toString() {
		return "RegisterFormBean [name=" + name + ", password=" + password
				+ ", cellphone=" + cellphone + ", email=" + email
				+ ", address=" + address + "]";
	}
	
}

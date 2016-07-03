package com.benqcz.ikanke.domain.impl;

import com.benqcz.ikanke.domain.Bean;

public class AccountBean implements Bean {

	private String id;
	private String code;
	private String name;
	private String password;
	private String cellphone;
	private String email;
	private String address;
	
	public AccountBean() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
	public String toString() {
		return "AccountBean [id=" + id + ", code=" + code + ", name=" + name
				+ ", password=" + password + ", cellphone=" + cellphone
				+ ", email=" + email + ", address=" + address + "]";
	}
	
}

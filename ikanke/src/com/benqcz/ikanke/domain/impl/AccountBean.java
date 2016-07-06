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
	private int status;
	
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return String
				.format("AccountBean [id=%s, code=%s, name=%s, password=%s, cellphone=%s, email=%s, address=%s, status=%s]",
						id, code, name, password, cellphone, email, address,
						status);
	}
	
}

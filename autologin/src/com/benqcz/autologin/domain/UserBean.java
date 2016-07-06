package com.benqcz.autologin.domain;

import java.io.Serializable;

public class UserBean implements Serializable {
	
	private String name;
	private String password;
	private String email;
	
	public UserBean() {}

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

	@Override
	public String toString() {
		return String.format("UserBean [email=%s, name=%s, password=%s]",
				email, name, password);
	}
	
}

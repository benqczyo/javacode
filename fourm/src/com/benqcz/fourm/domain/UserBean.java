package com.benqcz.fourm.domain;

import java.io.Serializable;

public class UserBean implements Serializable {
	
	private String username;
	private String password;
	private String email;
	private String birthday;
	
	public UserBean() {
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return String.format(
				"UserBean [birthday=%s, email=%s, password=%s, username=%s]",
				birthday, email, password, username);
	}

}

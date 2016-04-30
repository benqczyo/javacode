package com.benqcz.discuss.domain;

public class User {

	private String username;
	private String password;
	private String email;
	private String birthday;

	public User() {
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
		return "User [birthday=" + birthday + ", email=" + email
				+ ", password=" + password + ", username=" + username + "]";
	}

}

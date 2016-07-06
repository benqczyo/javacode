package com.benqcz.crm.domain;

import java.io.Serializable;
import java.util.Date;

public class CustomerBean implements Serializable {
	
	private int id;
	private String name;
	private int gender;
	private Date birthday;
	private String cellphone;
	private String email;
	private String preference;
	private int type;
	private String description;
	
	public CustomerBean() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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

	public String getPreference() {
		return preference;
	}

	public void setPreference(String preference) {
		this.preference = preference;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return String
				.format(
						"UserBean [birthday=%s, cellphone=%s, description=%s, email=%s, gender=%s, id=%s, name=%s, preference=%s, type=%s]",
						birthday, cellphone, description, email, gender, id,
						name, preference, type);
	}
	
}

package com.benqcz.crm.web.formbean;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

public class UpdateFormBean {

	private String id;
	private String name;
	private String gender;
	private String birthday;
	private String cellphone;
	private String email;
	private String preference;
	private String type;
	private String description;
	
	private Map<String, String> errors = new HashMap<String, String>();
	
	public boolean validate() {
		
		if (name == null || name.trim().equals("")) {
			errors.put("name", "请输入姓名");
		} else if (name.length() > 40) {
			errors.put("name", "姓名长度超过40个字符");
		}
		
		if (gender == null || gender.trim().equals("")) {
			errors.put("gender", "请选择性别");
		}
		
		if (birthday == null || birthday.trim().equals("")) {
			errors.put("birthday", "请输入出生日期");
		} else {
			try {
				new DateLocaleConverter().convert(birthday);
			} catch (Exception e) {
				errors.put("birthday", "出生日期格式不正确，eg.2001-01-01");
			}
		}
		
		if (cellphone == null || cellphone.trim().equals("")) {
			errors.put("cellphone", "请输入移动电话号码");
		} else if (!cellphone.matches("1[3|5|7|8|][0-9]{9}")) {
			errors.put("cellphone", "移动电话格式不正确");
		}
		
		if (email == null || email.trim().equals("")) {
			errors.put("email", "请输入电子邮箱");
		} else if (!email.matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")) {
			errors.put("email", "邮箱格式不正确");
		}
		
		if (preference == null || preference.trim().equals("")) {
			errors.put("preference", "请输入爱好");
		} else if (preference.length() > 40) {
			errors.put("preference", "爱好描述超过40个字符");
		}
		
		if (type == null || type.trim().equals("")) {
			errors.put("type", "请选择用户类型");
		}
		
		if (description == null || description.trim().equals("")) {
			errors.put("description", "请输入个人简介");
		} else if (description.length() > 100) {
			errors.put("description", "个人简介超过100个字符");
		}
		
		return errors.isEmpty();
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, String> getErrors() {
		return errors;
	}
	
}

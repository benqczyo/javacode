package com.benqcz.discuss.web.formbean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

public class RegisterFormBean {

	private String username;
	private String password;
	private String email;
	private String birthday;
	
	private Map<String, String> errors = new HashMap<String, String>();

	public RegisterFormBean() {}

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
	
	public Map<String, String> getErrors() {
		return errors;
	}

	public boolean validate() {

		if (username == null || username.trim().equals("")) {
			errors.put("username", "用户名为空");
		} else if (!username.matches("^[a-zA-Z]{3,8}$")) {
			errors.put("username", "用户名为3-8位字母组合");
		}
		
		if (password == null || password.trim().equals("")) {
			errors.put("password", "密码为空");
		} else if (!password.matches("^[0-9]{8,}$")) {
			errors.put("password", "密码为不少于8位数字组合");
		}
		
		if (email == null || email.trim().equals("")) {
			errors.put("email", "邮箱为空");
		} else if (!email.matches("^([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$")) {
			errors.put("email", "邮箱格式不正确");
		}

		if (birthday == null || birthday.trim().equals("")) {
			errors.put("birthday", "生日为空");
		} else {
			try {
				new DateLocaleConverter().convert(birthday);
			} catch (Exception e) {
				errors.put("birthday", "生日日期格式不正确");
			}
		}
		
		return errors.isEmpty();
	}

	@Override
	public String toString() {
		return String
				.format(
						"RegisterFormBean [birthday=%s, email=%s, password=%s, username=%s]",
						birthday, email, password, username);
	}

}

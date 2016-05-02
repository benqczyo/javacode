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
			errors.put("username", "�û���Ϊ��");
		} else if (!username.matches("^[a-zA-Z]{3,8}$")) {
			errors.put("username", "�û���Ϊ3-8λ��ĸ���");
		}
		
		if (password == null || password.trim().equals("")) {
			errors.put("password", "����Ϊ��");
		} else if (!password.matches("^[0-9]{8,}$")) {
			errors.put("password", "����Ϊ������8λ�������");
		}
		
		if (email == null || email.trim().equals("")) {
			errors.put("email", "����Ϊ��");
		} else if (!email.matches("^([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$")) {
			errors.put("email", "�����ʽ����ȷ");
		}

		if (birthday == null || birthday.trim().equals("")) {
			errors.put("birthday", "����Ϊ��");
		} else {
			try {
				new DateLocaleConverter().convert(birthday);
			} catch (Exception e) {
				errors.put("birthday", "�������ڸ�ʽ����ȷ");
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

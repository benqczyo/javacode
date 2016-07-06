package com.benqcz.fourm.web.formbean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

public class RegisterFormBean {
	
	private static final String EMAIL_REGEX = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";  
	private static final String USERNAME_REGEX = "^[a-zA-Z]{5,12}$";
	private static final String PASSWORD_REGEX = "^(?!_)(?!.*?_$)[a-zA-Z0-9_]{8,12}$";
	
	private String email;
	private String username;
	private String password;
	private String password2;
	private String birthday;
	
	private Map<String, String> errors = new HashMap<String, String>();
	
	public RegisterFormBean() {}
	
	public boolean validate() {
		
		if (email == null || email.trim().equals("")) {
			errors.put("email", "�����������ַ");
		} else {
			if (!email.matches(EMAIL_REGEX)) {
				errors.put("email", "�����ʽ����ȷ");
			}
		}
		
		if (username == null || username.trim().equals("")) {
			errors.put("username", "�������û���");
		} else {
			if (!username.matches(USERNAME_REGEX)) {
				errors.put("username", "�û�����ʽ����ȷ,������5~12λ��ĸ���");
			}
		}
		
		if (password == null || password.trim().equals("")) {
			errors.put("password", "����������");
		} else {
			if (!password.matches(PASSWORD_REGEX)) {
				errors.put("password", "�����ʽ����ȷ,������8-12λ�����»��߿�ͷ�ͽ�β����ĸ���");
			}
		}
		
		if(!password2.equals(password)){
			errors.put("password2", "�����������һ��");
		}
		
		if (birthday == null || birthday.trim().equals("")) {
			errors.put("birthday", "�������������");
		} else {
			try {
				new DateLocaleConverter().convert(birthday);
			} catch (Exception e) {
				errors.put("birthday", "��������ȷ����,��2000-01-01");
			}
		}
		
		return errors.isEmpty();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
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
	
}

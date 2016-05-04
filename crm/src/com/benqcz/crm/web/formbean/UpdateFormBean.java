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
			errors.put("name", "����������");
		} else if (name.length() > 40) {
			errors.put("name", "�������ȳ���40���ַ�");
		}
		
		if (gender == null || gender.trim().equals("")) {
			errors.put("gender", "��ѡ���Ա�");
		}
		
		if (birthday == null || birthday.trim().equals("")) {
			errors.put("birthday", "�������������");
		} else {
			try {
				new DateLocaleConverter().convert(birthday);
			} catch (Exception e) {
				errors.put("birthday", "�������ڸ�ʽ����ȷ��eg.2001-01-01");
			}
		}
		
		if (cellphone == null || cellphone.trim().equals("")) {
			errors.put("cellphone", "�������ƶ��绰����");
		} else if (!cellphone.matches("1[3|5|7|8|][0-9]{9}")) {
			errors.put("cellphone", "�ƶ��绰��ʽ����ȷ");
		}
		
		if (email == null || email.trim().equals("")) {
			errors.put("email", "�������������");
		} else if (!email.matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")) {
			errors.put("email", "�����ʽ����ȷ");
		}
		
		if (preference == null || preference.trim().equals("")) {
			errors.put("preference", "�����밮��");
		} else if (preference.length() > 40) {
			errors.put("preference", "������������40���ַ�");
		}
		
		if (type == null || type.trim().equals("")) {
			errors.put("type", "��ѡ���û�����");
		}
		
		if (description == null || description.trim().equals("")) {
			errors.put("description", "��������˼��");
		} else if (description.length() > 100) {
			errors.put("description", "���˼�鳬��100���ַ�");
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

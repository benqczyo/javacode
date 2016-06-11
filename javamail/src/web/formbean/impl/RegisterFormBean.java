package web.formbean.impl;

import web.formbean.AbstractFormBean;

public class RegisterFormBean extends AbstractFormBean {
	
	private String name;
	private String password;
	private String password2;
	private String email;
	
	public RegisterFormBean() {
		
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

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean isValidated() {
		if (name == null || name.trim().isEmpty())
			messages.put("name", "请输入账号名称");
		if (password == null || password.trim().isEmpty())
			messages.put("password", "请输入密码");
		if (password2 == null || password2.trim().isEmpty())
			messages.put("password2", "请重复密码");
		else if (!password2.equalsIgnoreCase(password))
			messages.put("password2", "两次密码不一致");
		if (email == null || email.trim().isEmpty())
			messages.put("email", "请输入电子邮件地址");
		return super.isValidated();
	}

	@Override
	public String toString() {
		return String
				.format("RegisterFormBean [name=%s, password=%s, password2=%s, email=%s]",
						name, password, password2, email);
	}

}

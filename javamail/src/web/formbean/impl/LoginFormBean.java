package web.formbean.impl;

import web.formbean.AbstractFormBean;

public class LoginFormBean extends AbstractFormBean {
	
	private String name;
	private String password;
	
	public LoginFormBean() {
		
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

	@Override
	public boolean isValidated() {
		if (name == null || name.trim().isEmpty())
			messages.put("name", "«Î ‰»Î’À∫≈√˚≥∆");
		if (password == null || password.trim().isEmpty())
			messages.put("password", "«Î ‰»Î√‹¬Î");
		return super.isValidated();
	}

	@Override
	public String toString() {
		return String.format("LoginFormBean [name=%s, password=%s]", name,
				password);
	}
	
}

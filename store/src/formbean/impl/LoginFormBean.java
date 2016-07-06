package formbean.impl;

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

	public boolean validate() {
		if (name == null || name.trim().equals("")) messages.put("name", "�������˺�");
		if (password == null || password.trim().equals("")) messages.put("password", "����������");
		return messages.isEmpty();
	}
	
	@Override
	public String toString() {
		return String.format("LoginFormBean [name=%s, password=%s]", name,
				password);
	}

}

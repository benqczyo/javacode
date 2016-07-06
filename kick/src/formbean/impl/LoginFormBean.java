package formbean.impl;

public class LoginFormBean extends AbstractFormBeanImpl {
	
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
		if (name == null || name.trim().equals(""))
			messages.put("name", "请输入用户名");
		if (password == null || password.trim().equals(""))
			messages.put("password", "请输入密码");
		return super.isValidated(); 
	}

	@Override
	public String toString() {
		return String.format("LoginFormBean [name=%s, password=%s]", name,
				password);
	}
	
}

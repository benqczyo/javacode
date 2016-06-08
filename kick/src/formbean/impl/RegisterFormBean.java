package formbean.impl;

public class RegisterFormBean extends AbstractFormBeanImpl {
	
	private String name;
	private String password;
	private String password2;

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
	
	@Override
	public boolean isValidated() {
		if (name == null || name.trim().equals(""))
			messages.put("name", "请输入用户名");
		if (password == null || password.trim().equals(""))
			messages.put("password", "请输入密码");
		if (password2 == null || password2.trim().equals(""))
			messages.put("password2", "请输入确认密码");
		else if (!password2.equals(password))
			messages.put("password2", "两次密码不匹配");
		return super.isValidated(); 
	}
	
	@Override
	public String toString() {
		return String.format(
				"RegisterFormBean [name=%s, password=%s, password2=%s]", name,
				password, password2);
	}

}

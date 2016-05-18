package com.benqcz.loginex.web.formbean.impl;

public class RegisterFormBean extends AbstractFormBeanImpl {
	
	private String name;
	private String password;
	private String email;

	public boolean validate() {
		if (name == null || name.trim().equals("")) messages.put("name", "«Î ‰»Î–’√˚");
		if (password == null || password.trim().equals("")) messages.put("password", "«Î ‰»Î√‹¬Î");
		if (email == null || email.trim().equals("")) messages.put("email", "«Î ‰»Î” œ‰");
		return messages.isEmpty() ;
	}

}

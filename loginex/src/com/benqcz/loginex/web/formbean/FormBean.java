package com.benqcz.loginex.web.formbean;

import java.util.Map;

public interface FormBean {
	boolean validate();
	Map<String, String> getMessages();
}

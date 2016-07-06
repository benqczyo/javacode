package com.benqcz.autologin.web.formbean;

import java.util.Map;

public interface FormBean {
	boolean validate();
	Map<String, String> getMessages();
}

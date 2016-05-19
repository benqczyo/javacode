package com.benqcz.autologin.web.formbean.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.benqcz.autologin.web.formbean.FormBean;

public abstract class AbstractFormBeanImpl implements FormBean, Serializable {
	
	protected Map<String, String> messages = new HashMap<String, String>();

	public Map<String, String> getMessages() {
		return messages;
	}
	
}

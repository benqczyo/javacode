package formbean.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import formbean.FormBean;

public abstract class AbstractFormBeanImpl implements FormBean, Serializable {
	
	protected Map<String, String> messages = new HashMap<String, String>();

	@Override
	public Map<String, String> getMessages() {
		return messages;
	}

	@Override
	public boolean isValidated() {
		return messages.isEmpty();
	}
	
}

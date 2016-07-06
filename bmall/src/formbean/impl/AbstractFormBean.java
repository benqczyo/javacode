package formbean.impl;

import java.util.HashMap;
import java.util.Map;

import formbean.FormBean;

public abstract class AbstractFormBean implements FormBean {
	
	protected Map<String, String> messages = new HashMap<String, String>();
	
	public boolean isValidated() {
		return messages.isEmpty();
	}

	public Map<String, String> getMessages() {
		return messages;
	}

	public void setMessages(Map<String, String> messages) {
		this.messages = messages;
	}
}

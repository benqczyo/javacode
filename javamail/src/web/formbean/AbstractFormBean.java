package web.formbean;

import java.util.HashMap;
import java.util.Map;


public abstract class AbstractFormBean implements FormBean {
	
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

package web.formbean;

import java.util.Map;

public interface FormBean {
	Map<String, String> getMessages();
	boolean isValidated();
}

package formbean;

import java.util.Map;

public interface FormBean {
	boolean isValidated();
	Map<String, String> getMessages();
}

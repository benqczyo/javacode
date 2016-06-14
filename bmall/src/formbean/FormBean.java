package formbean;

import java.io.Serializable;
import java.util.Map;

public interface FormBean extends Serializable {
	boolean isValidated();
	Map<String, String> getMessages();
}

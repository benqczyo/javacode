package formbean;

import java.io.Serializable;
import java.util.Map;

public interface FormBean extends Serializable {
	boolean validate();
	Map<String, String> getMessages();
}

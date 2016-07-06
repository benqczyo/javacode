package cookie;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class UserFactory {
	
	public static User create(Map<String, String[]> items) throws Exception {
		User result = new User();
		for (Entry<String, String[]> item : items.entrySet()) {
			try {
				PropertyDescriptor dp = new PropertyDescriptor(item.getKey(), User.class);
				dp.getWriteMethod().invoke(result, item.getValue()[0]);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
			
		}
		return result;
	}
	
}

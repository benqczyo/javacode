package dao;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class UserFactory {
	
	public static User create(ResultSet result) throws Exception {
		User user = null;
		try {
			Class c = User.class;
			if (result.next()) {
				user = new User();
				ResultSetMetaData rsm = result.getMetaData();
				for (int i = 1; i <= rsm.getColumnCount(); i++) {
					String name = rsm.getColumnName(i).toLowerCase();
					PropertyDescriptor dp = new PropertyDescriptor(name, c);
					Method method = dp.getWriteMethod();
					String type = c.getDeclaredField(name).getType().getName();
					Object value = null;
					if ("int".equalsIgnoreCase(type)) {
						value = result.getInt(rsm.getColumnLabel(i));
					} else if ("java.lang.String".equalsIgnoreCase(type)) {
						value = result.getString(rsm.getColumnLabel(i));
					}
					method.invoke(user, value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return user;
	}



}

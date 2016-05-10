package com.benqcz.database.assistant.handler;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

import com.benqcz.database.assistant.exception.AssistantException;

public abstract class AbstractBeanHandler implements ResultSetHandler {
	
	protected Class beanClass;
	
	protected Object parse(ResultSet rs, Class beanClass) {
		Object result = null;
		ResultSetMetaData rsm = null;
		try {
			while (rs.next()) {
				if (result == null) result = beanClass.newInstance();
				if (rsm == null) rsm = rs.getMetaData();
				for (int index = 1; index <= rsm.getColumnCount(); index++) {
					PropertyDescriptor pd = new PropertyDescriptor(rsm.getColumnName(index).toLowerCase(), beanClass);
					Method writeMethod = pd.getWriteMethod();
					String type = pd.getPropertyType().getName().toLowerCase();
					type = type.substring(type.lastIndexOf('.') + 1);
					type = type.substring(0, 1).toUpperCase().concat(type.substring(1));
					Method readMethod = rs.getClass().getDeclaredMethod("get" + type, int.class);
					writeMethod.invoke(result, readMethod.invoke(rs, index));
				}
			}
		} catch (Exception e) {
			throw new AssistantException(e);
		}
		return result;
	}

}

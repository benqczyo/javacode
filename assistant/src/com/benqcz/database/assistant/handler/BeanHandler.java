package com.benqcz.database.assistant.handler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import com.benqcz.database.assistant.exception.AssistantException;

public class BeanHandler extends AbstractBeanHandler {
	
	public BeanHandler(Class beanClass) {
		super(beanClass);
	}

	@Override
	public Object handle(ResultSet rs) {
		Object result = null;
		ResultSetMetaData rsm = null;
		try {
			 if (rs.next()) {
				if (result == null) result = beanClass.newInstance();
				if (rsm == null) rsm = rs.getMetaData();
				for (int index = 1; index <= rsm.getColumnCount(); index++) {
					PropertyDescriptor pd = new PropertyDescriptor(rsm.getColumnName(index).toLowerCase(), beanClass);
					Method writeMethod = pd.getWriteMethod();
					String type = pd.getPropertyType().getName().toLowerCase();
					type = type.substring(type.lastIndexOf('.') + 1);
					type = type.substring(0, 1).toUpperCase().concat(type.substring(1));
					Method readMethod = rs.getClass().getMethod("get" + type, int.class);
					writeMethod.invoke(result, readMethod.invoke(rs, index));
				}
			}
		} catch (Exception e) {
			throw new AssistantException(e);
		}
		return result;
	}

}

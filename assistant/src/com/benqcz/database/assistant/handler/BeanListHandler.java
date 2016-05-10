package com.benqcz.database.assistant.handler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import com.benqcz.database.assistant.exception.AssistantException;

public class BeanListHandler extends AbstractBeanHandler {

	public BeanListHandler(Class beanClass) {
		super(beanClass);
	}

	@Override
	public Object handle(ResultSet rs) {
		List result = null;
		ResultSetMetaData rsm = null;
		try {
			 while (rs.next()) {
				if (result == null) result = new ArrayList();
				if (rsm == null) rsm = rs.getMetaData();
				Object bean = beanClass.newInstance();
				for (int index = 1; index <= rsm.getColumnCount(); index++) {
					PropertyDescriptor pd = new PropertyDescriptor(rsm.getColumnName(index).toLowerCase(), beanClass);
					Method writeMethod = pd.getWriteMethod();
					String type = pd.getPropertyType().getName().toLowerCase();
					type = type.substring(type.lastIndexOf('.') + 1);
					type = type.substring(0, 1).toUpperCase().concat(type.substring(1));
					Method readMethod = rs.getClass().getMethod("get" + type, int.class);
					writeMethod.invoke(bean, readMethod.invoke(rs, index));
				}
				result.add(bean);
			}
		} catch (Exception e) {
			throw new AssistantException(e);
		}
		return (Object) result;
	}

}

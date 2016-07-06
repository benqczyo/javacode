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
	
	public AbstractBeanHandler(Class beanClass) {
		this.beanClass = beanClass;
	}

}

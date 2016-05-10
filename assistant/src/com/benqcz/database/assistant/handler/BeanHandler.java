package com.benqcz.database.assistant.handler;

import java.sql.ResultSet;

public class BeanHandler extends AbstractBeanHandler {
	
	public BeanHandler(Class beanClass) {
		this.beanClass = beanClass;
	}

	@Override
	public Object handle(ResultSet rs) {
		return parse(rs, beanClass);
	}

}

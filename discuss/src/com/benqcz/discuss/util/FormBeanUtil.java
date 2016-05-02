package com.benqcz.discuss.util;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public abstract class FormBeanUtil {

	public static <T> T fillFormBean(HttpServletRequest request, Class<T> beanClass) {
		T result = null;
		try {
			result = beanClass.newInstance();
			BeanUtils.populate(result, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}
	
}

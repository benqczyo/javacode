package com.benqcz.ikanke.utils;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.benqcz.ikanke.exception.FormBeanUtilsException;
import com.benqcz.ikanke.formbean.FormBean;

public abstract class FormBeanUtils {
	public static <T extends FormBean> T fill(Class<T> clazz, HttpServletRequest request) {
		T result = null;
		try {
			result = clazz.newInstance();
			BeanUtils.populate(result, request.getParameterMap());
		} catch (Exception e) {
			throw new FormBeanUtilsException(e);
		}
		return result;
	}
}

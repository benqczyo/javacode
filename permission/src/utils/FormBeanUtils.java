package utils;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;


public class FormBeanUtils {

	public static <T> T fill(HttpServletRequest request, Class<T> clazz) throws Exception {
		T result = clazz.newInstance();
		BeanUtils.populate(result, request.getParameterMap());
		return result;
	}

}

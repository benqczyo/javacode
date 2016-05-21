package com.benqcz.filter.filter;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class EncodingFilter implements Filter {

	private FilterConfig filterConfig;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		
		String param = filterConfig.getInitParameter("preference");
		final String encoding = param == null ? "UTF-8" : param;
		
		Class clazz = request.getClass();
		ClassLoader loader = clazz.getClassLoader();
		Class[] interfaces = clazz.getInterfaces();
		
		final ServletRequest req = request;
		
		ServletRequest requestProxy = (ServletRequest) Proxy.newProxyInstance(loader, interfaces, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				Object result = null;
				if ("getParameter".equalsIgnoreCase(method.getName())) {
					HttpServletRequest innerReq = (HttpServletRequest) req;
					if ("post".equalsIgnoreCase(innerReq.getMethod())) 
						innerReq.setCharacterEncoding(encoding);
					result = method.invoke(req, args);
					if ("get".equalsIgnoreCase(innerReq.getMethod())) {
						result = new String(((String) result).getBytes("ISO-8859-1"), encoding);
					}
				} else {
					result = method.invoke(req, args);
				}
				return result;
			}
		});
		
		response.setContentType("text/html;charset=" + encoding);
		filterChain.doFilter(requestProxy, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

}

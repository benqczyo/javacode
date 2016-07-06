package com.benqcz.filter.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class CharacterEncodingFilter implements Filter {

	private FilterConfig filterConfig;

	private class CharacterEncodingFilterRequest extends
			HttpServletRequestWrapper {

		public CharacterEncodingFilterRequest(HttpServletRequest request) {
			super(request);
		}

		@Override
		public String getParameter(String name) {
			String result = super.getParameter(name);
			if ("get".equalsIgnoreCase(getMethod())) {
				try {
					if (result != null)
						result = new String(result.getBytes("ISO-8859-1"), getCharacterEncoding());
					System.out.println(result);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
			return result;
		}

	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		String encoding = request.getParameter("preference");
		request.setCharacterEncoding(encoding = (encoding == null ? "UTF-8" : encoding));
		response.setContentType("text/html;charset=" + encoding);
		filterChain.doFilter(new CharacterEncodingFilterRequest((HttpServletRequest) request), response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

}
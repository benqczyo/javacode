package com.benqcz.ikanke.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodeFilter implements Filter {
	
	private FilterConfig filterConfig;

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String encode = filterConfig.getInitParameter("encode");
		encode = (encode == null) ? "UTF-8" : encode;
		request.setCharacterEncoding(encode);
		response.setCharacterEncoding(encode);
		response.setContentType("text/html;charset=" + encode);
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

}

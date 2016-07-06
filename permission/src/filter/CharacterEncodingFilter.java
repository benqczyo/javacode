package filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class CharacterEncodingFilter implements Filter {
	
	private FilterConfig config;
	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		String encoding = config.getInitParameter("encoding");
		request.setCharacterEncoding(encoding = (encoding == null ? "UTF-8" : encoding));
		response.setCharacterEncoding(encoding);
		response.setContentType("text/html;charset=" + encoding);
		filterChain.doFilter((HttpServletRequest) request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

}

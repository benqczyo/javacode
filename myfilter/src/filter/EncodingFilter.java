package filter;

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

public class EncodingFilter implements Filter {

	private FilterConfig filterConfig;

	private class EncodingFilterRequest extends HttpServletRequestWrapper {

		public EncodingFilterRequest(HttpServletRequest request) {
			super(request);
		}

		@Override
		public String getParameter(String name) {
			String result = super.getParameter(name);
			if ("get".equalsIgnoreCase(super.getMethod())) {
				if (result != null) {
					try {
						result = new String(result.getBytes("ISO-8859-1"), super.getCharacterEncoding());
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
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
		String encoding = filterConfig.getInitParameter("preference");
		if (encoding == null) encoding = "UTF-8";
		request.setCharacterEncoding(encoding);
		response.setContentType("text/html;charset=" + encoding);
		filterChain.doFilter(new EncodingFilterRequest((HttpServletRequest) request), response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

}

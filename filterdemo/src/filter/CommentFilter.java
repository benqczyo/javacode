package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommentFilter implements Filter {
	
	private String[] illegalWords = new String[] {"fuck", "damn", "ÄãÂèµÄ"};

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		
		String illegal = null;
		String comment = req.getParameter("comment");
		for (String word : illegalWords) {
			if (comment.indexOf(word) != -1) {
				illegal = word;
				break;
			}
		}
		
		if (illegal != null)
			resp.getWriter().print(String.format("<h2>Ãô¸Ð´Ê£º%s</h2>", illegal));
		else
			chain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}
	
}

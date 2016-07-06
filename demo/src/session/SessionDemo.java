package session;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionDemo extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(10);
		// session.invalidate();
		Integer count = (Integer) session.getAttribute("count");
		session.setAttribute("count", count = (count == null ? 1 : count + 1));
		
		response.getWriter().print(String.format("<strong>%s</strong>", count));
		
	}
}

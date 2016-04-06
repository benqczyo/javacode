package servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.User;
import dao.UserDAOImpl;

public class ActionRouter extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if (session.getAttribute("validated") != null)
			request.getRequestDispatcher("/main.jsp").forward(request, response);
	
		String uri = request.getRequestURI();
		String cmd = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));
		
		if ("login".equalsIgnoreCase(cmd)) {
			session.setAttribute("login", true);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
		if ("validate".equalsIgnoreCase(cmd)) {
			User validatingUser = new User();
			validatingUser.setName(request.getParameter("name"));
			validatingUser.setPassword(request.getParameter("password"));
			try {
				User user = new UserDAOImpl().findUserByName(validatingUser.getName());
				if (user != null && user.equals(validatingUser)) {
					session.setAttribute("validated", true);
					session.setAttribute("name", user.getName());
					response.sendRedirect("/login/main.do");
				} else {
					session.setAttribute("error", "’À∫≈ªÚ√‹¬Î¥ÌŒÛ");
					response.sendRedirect("/login/login.do");
				}
			} catch (Exception e) {
				e.printStackTrace();
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
		}
	}
}

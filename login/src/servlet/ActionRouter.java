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
		
		String uri = request.getRequestURI();
		String cmd = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));
		
		if ("login".equalsIgnoreCase(cmd)) {
			request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
		}
		
		if ("validate".equalsIgnoreCase(cmd)) {
			User validatingUser = new User();
			validatingUser.setName(request.getParameter("name"));
			validatingUser.setPassword(request.getParameter("password"));
			try {
				User user = new UserDAOImpl().findUserByName(validatingUser.getName());
				if (user != null && user.equals(validatingUser)) {
					session.setAttribute("name", user.getName());
					response.sendRedirect("/login/WEB-INF/pages/main");
				} else {
					session.setAttribute("error", "’À∫≈ªÚ√‹¬Î¥ÌŒÛ");
					response.sendRedirect("/login/WEB-INF/pages/login");
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("/login/WEB-INF/pages/error");
			}
		}
	}
}

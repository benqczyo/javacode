package servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionRouter extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		String uri = request.getRequestURI();
		String cmd = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));
		
		if ("login".equalsIgnoreCase(cmd)) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
		
	}
}

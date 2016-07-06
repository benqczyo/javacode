package servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Validate extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		String captcha = request.getParameter("captcha");
		
		if (user.equalsIgnoreCase(new StringBuffer(password).reverse().toString()) 
				&& captcha.equalsIgnoreCase((String) request.getSession().getAttribute("captcha"))) {
			
			request.getSession().setAttribute("user", user);
			response.sendRedirect("/login2/index.jsp");
			return;
		
		}
			
		response.sendRedirect("/login2/login.jsp");
		
	}
}

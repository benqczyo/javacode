package cookie;


import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionRouter extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String cmd = uri.substring(uri.lastIndexOf("/") + 1);
		
		if ("login".equalsIgnoreCase(cmd)) {
			
			String user = request.getParameter("user");
			String password = request.getParameter("password");
			String keep = request.getParameter("keep");
			
			Cookie cookieUser = new Cookie("user", URLEncoder.encode(user, "utf-8"));
			Cookie cookiePassword = new Cookie("password", URLEncoder.encode(password, "utf-8"));
			cookieUser.setPath(getServletContext().getContextPath());
			cookiePassword.setPath(getServletContext().getContextPath());
			
			if (keep != null) {
				cookieUser.setMaxAge(60 * 5);
				cookiePassword.setMaxAge(60 * 5);
			} else {
				cookieUser.setMaxAge(0);
				cookiePassword.setMaxAge(0);
			}
			
			response.addCookie(cookieUser);
			response.addCookie(cookiePassword);
		}
		
	}
}

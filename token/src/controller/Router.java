package controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Router extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		if(req.getParameter("action").equalsIgnoreCase("login")) {
			HttpSession session = req.getSession();
			String tokenFromSession = (String) session.getAttribute("token");
			String tokenFromClient = (String) req.getParameter("token");
			if (tokenFromClient.equalsIgnoreCase(tokenFromSession)) {
				System.out.println(tokenFromSession + "=" + tokenFromClient);
				session.removeAttribute("token");
			} else {
				System.out.println(tokenFromSession + "=" + tokenFromClient);
			}
		}
			
	}

}

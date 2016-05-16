package com.benqcz.login.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Router extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String action = req.getParameter("action");
		String path = "/WEB-INF/pages/login.jsp";

		if (action == null) {
			Cookie[] cookies = req.getCookies();
			if (cookies != null) {
				for (Cookie c : cookies) {
					if (c.getName().equalsIgnoreCase("user")) {
						req.setAttribute("user", c.getValue());
						path = "/WEB-INF/pages/main.jsp";
						break;
					}
				}
			}
		} else if (action.equalsIgnoreCase("check")) {
			String user = req.getParameter("user");
			String pass = req.getParameter("pass");
			String auto = req.getParameter("auto");
			if (!user.equalsIgnoreCase("")
					&& user.equalsIgnoreCase(new StringBuffer(pass).reverse()
							.toString())) {
				Cookie cookie = new Cookie("user", user);
				cookie.setPath(getServletContext().getContextPath());
				cookie.setMaxAge(auto != null ? 60 * 5 : 0);
				req.setAttribute("user", user);
				resp.addCookie(cookie);
				path = "/WEB-INF/pages/main.jsp";
			}
		} else if (action.equalsIgnoreCase("login")) {
			path = "/WEB-INF/pages/login.jsp";
		} else if (action.equalsIgnoreCase("logout")) {
			Cookie cookie = new Cookie("user", null);
			cookie.setPath(getServletContext().getContextPath());
			cookie.setMaxAge(0);
			resp.addCookie(cookie);
			path = "/WEB-INF/pages/login.jsp";
		} else {
			path = "/WEB-INF/pages/error.jsp";
		}

		req.getRequestDispatcher(path).forward(req, resp);
	}

}

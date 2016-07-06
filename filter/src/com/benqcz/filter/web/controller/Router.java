package com.benqcz.filter.web.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Router extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("login".equalsIgnoreCase(action)) {
			response.getWriter().write(request.getParameter("name"));
			response.getWriter().write(request.getParameter("password"));
			return;
		}
		if ("comment".equalsIgnoreCase(action)) {
			response.getWriter().write(request.getParameter("comment"));
			return;
		}
	}

}

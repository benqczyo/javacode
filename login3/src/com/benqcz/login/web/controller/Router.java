package com.benqcz.login.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Router extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String uri = req.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));
		
		System.out.println(uri);
		
	}
	
}

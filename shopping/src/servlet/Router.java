package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.ProductDB;

public class Router extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));
		
		if ("list".equalsIgnoreCase(action)) {
			request.setAttribute("products", ProductDB.findAll());
			request.getRequestDispatcher("/list.jsp").forward(request, response);
		}
		
		
	}
}

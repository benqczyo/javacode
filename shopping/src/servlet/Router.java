package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.Product;
import db.ProductDB;

public class Router extends HttpServlet {
	
	protected String makeIds(HttpServletRequest request, String id) {
		
		Cookie[] cookies = request.getCookies();
		if (cookies == null)
			return id;
		
		Cookie cookie = null;
		for (Cookie c : cookies) {
			if ("lasted".equalsIgnoreCase(c.getName())) {
				cookie = c;
				break;
			}
		}
		if (cookie == null)
			return id;
		
		LinkedList<String> list = new LinkedList<String>(Arrays.asList(cookie.getValue().split("-")));
		list.removeAll(Arrays.asList(new String[]{id}));
		list.push(id);
		if (list.size() > 4) list.removeLast();
		Object[] arr = list.toArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			if (i > 0) sb.append("-");
			sb.append(arr[i]);
		}
		return sb.toString();
	
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));
	
		if ("list".equalsIgnoreCase(action)) {
			List<Product> lastedProducts = null;
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie c : cookies) {
					if ("lasted".equalsIgnoreCase(c.getName())) {
						if (lastedProducts == null) lastedProducts = new ArrayList<Product>();
						for (String id : c.getValue().split("-"))
							lastedProducts.add(ProductDB.findProductById(Integer.parseInt(id)));
					}
				}
				request.setAttribute("lastedProducts", lastedProducts);
			}
			request.setAttribute("products", ProductDB.findAll());
			request.getRequestDispatcher("/list.jsp").forward(request, response);
			return;
		}
		
		if ("detail".equalsIgnoreCase(action)) {
			String value = request.getParameter("id");
			int id = -1;
			if (value == null || value.matches("[0-9]+") == false 
					|| (id = Integer.parseInt(value)) > ProductDB.getSize()) {
				response.sendRedirect("/shopping/error.jsp");	
				return;
			}
			
			request.setAttribute("product", ProductDB.findProductById(id));
			
			Cookie c = new Cookie("lasted", makeIds(request, value));
			c.setPath(getServletContext().getContextPath());
			c.setMaxAge(Integer.MAX_VALUE);
			response.addCookie(c);
			
			request.getRequestDispatcher("/detail.jsp").forward(request, response);
		}
	}
}

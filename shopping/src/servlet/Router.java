package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.Product;
import db.ProductDB;

public class Router extends HttpServlet {
	
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
					if (c.getName().startsWith("lasted")) {
						if (lastedProducts == null) lastedProducts = new ArrayList<Product>();
						lastedProducts.add(ProductDB.findProductById(Integer.parseInt(c.getValue())));
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
			if (value == null || (id = Integer.parseInt(value)) > ProductDB.getSize()) {
				response.sendRedirect("/shopping/error.jsp");
				return;
			}
			Cookie cookie = new Cookie("lasted" + id, String.valueOf(id));
			response.addCookie(cookie);
			request.setAttribute("product", ProductDB.findProductById(id));
			request.getRequestDispatcher("/detail.jsp").forward(request, response);
			return;
		}
	}
}

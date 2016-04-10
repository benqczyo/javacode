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
import javax.servlet.http.HttpSession;

import cfg.Configer;
import db.Product;
import db.ProductDB;

public class Router extends HttpServlet {
	
	protected boolean isValid(String id) {
		return id == null || id.matches("[0-9]+") == false 
			|| Integer.parseInt(id) > ProductDB.getSize();
	}
	
	protected void showAll(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		List<Product> lasted = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (Configer.HISTORY_COOKIE_NAME.equalsIgnoreCase(c.getName())) {
					if (lasted == null) lasted = new ArrayList<Product>();
					for (String id : c.getValue().split("-"))
						lasted.add(ProductDB.findProductById(Integer.parseInt(id)));
					break;
				}
			}
			request.setAttribute(Configer.HISTORY_ATTR, lasted);
		}
		request.setAttribute(Configer.ALL_PROCUCTS_ATTR, ProductDB.findAll());
		request.getRequestDispatcher(Configer.SHOW_ALL_PAGE).forward(request, response);
	
	}
	
	protected void showDetail(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String value = request.getParameter("id");
		if (isValid(value)) {
			response.sendRedirect(getServletContext().getContextPath() + Configer.ERROR_PAGE);
			return;
		}
		
		StringBuffer sb = new StringBuffer();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (Configer.HISTORY_COOKIE_NAME.equalsIgnoreCase(c.getName())) {
					LinkedList<String> list = new LinkedList<String>(Arrays.asList(c.getValue().split("-")));
					list.removeAll(Arrays.asList(new String[] {value}));
					for (int i = 0; i < list.size(); i++) {
						if (i > 0) sb.append("-");
						sb.append(list.get(i));
					}
					break;
				}
			}
		}
		sb.insert(0, sb.length() == 0 ? value : value + "-");
		
		Cookie cookie = new Cookie(Configer.HISTORY_COOKIE_NAME, sb.toString());
		cookie.setPath(getServletContext().getContextPath());
		cookie.setMaxAge(Integer.MAX_VALUE);
		response.addCookie(cookie);
		request.setAttribute(Configer.PRODUCT_DETAIL_ATTR, ProductDB.findProductById(Integer.parseInt(value)));
		request.getRequestDispatcher(Configer.SHOW_DETAIL_PAGE).forward(request, response);
	
	}
	
	protected void add(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String value = request.getParameter("id");
		if (isValid(value)) {
			response.sendRedirect(getServletContext().getContextPath() + Configer.ERROR_PAGE);
			return;
		}
		
		HttpSession session = request.getSession();
		List<Product> list = (List<Product>) session.getAttribute(Configer.CART_ATTR);
		if (list == null) list = new ArrayList<Product>();
		list.add(0, ProductDB.findProductById(Integer.parseInt(value)));
		session.setAttribute(Configer.CART_ATTR, list);
		session.setMaxInactiveInterval(Integer.MAX_VALUE);
		Cookie cookie = new Cookie("JSESSIONID", session.getId());
		cookie.setMaxAge(Integer.MAX_VALUE);
		response.addCookie(cookie);
		
		response.sendRedirect(String.format("%s/%s.do", getServletContext().getContextPath(), Configer.LIST_ACTION));
	
	}
	
	protected void showCart(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.getRequestDispatcher(Configer.SHOW_CART_PAGE).forward(request, response);
		
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));
		
		if (Configer.LIST_ACTION.equalsIgnoreCase(action)) {
			showAll(request, response);
			return;
		}
		
		if (Configer.DETAIL_ACTION.equalsIgnoreCase(action)) {
			showDetail(request, response);
			return;
		}
		
		if (Configer.ADD_ACTION.equalsIgnoreCase(action)) {
			add(request, response);
			return;
		}
		
		if (Configer.CART_ACTION.equalsIgnoreCase(action)) {
			showCart(request, response);
			return;
		}
		
		response.sendRedirect(getServletContext().getContextPath() + Configer.ERROR_PAGE);
		
	}

}

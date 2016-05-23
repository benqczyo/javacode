package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BussinessService;
import service.BussinessServiceImpl;

public class Router extends HttpServlet {
	
	private BussinessService service = new BussinessServiceImpl();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if (action == null) action = "index";
		
		if ("index".equalsIgnoreCase(action)) {
			return;
		}
		
		if ("show".equalsIgnoreCase(action)) {
			showAction(request, response);
			return;
		}
		
		
	}

	private void showAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = null;
		String view = request.getParameter("view");
		if ("menu".equalsIgnoreCase(view)) {
			request.setAttribute("menus", service.findAllMenus());
			path = "/WEB-INF/pages/server/menu.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

}

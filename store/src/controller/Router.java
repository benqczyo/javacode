package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BussinessService;
import service.impl.BussinessServiceImpl;

import domain.MenuBean;
import domain.Page;

public class Router extends HttpServlet {
	
	private BussinessService service = new BussinessServiceImpl();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("listAllMenus".equalsIgnoreCase(action)) {
			listAllMenus(request, response);
			return;
		}
		if ("delMenus".equalsIgnoreCase(action)) {
			delMenus(request, response);
			return;
		}
	}

	private void listAllMenus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("page", getPage(new MenuBean(), request));
		request.getRequestDispatcher("/manager/listAllMenus.jsp").forward(request, response);
	}
	
	private void delMenus(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String[] ids = request.getParameterValues("ids");
		if (ids != null && ids.length > 0) {
			service.de
			service.delMenusByIds(ids);
			response.sendRedirect(request.getContextPath() + "/router?action=listAllMenus");
		}
	}
	
	private Page getPage(Object target, HttpServletRequest request) {
		String pageId = request.getParameter("pageId");
		if (pageId == null || pageId.trim().equals("") || !pageId.matches("[1-9]+")) pageId = "1";
		ServletConfig config = getServletConfig();
		String pageRange = config.getInitParameter("pageRange");
		String pageRecords = config.getInitParameter("pageRecords");
		if (pageRange == null || pageRange.trim().equals("") || !pageRange.matches("[1-9]+")) pageRange = "4";
		if (pageRecords == null || pageRecords.trim().equals("") || !pageRecords.matches("[1-9]+")) pageRecords = "10";
		return service.getPage(target, Integer.parseInt(pageRange), Integer.parseInt(pageRecords), pageId);
	}
	
}

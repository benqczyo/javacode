package controller;

import java.awt.MenuBar;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BEncoderStream;

import domain.AccountBean;
import domain.MenuBean;
import domain.Page;
import domain.RoleBean;
import exception.DaoException;

import formbean.impl.AddAccountFormBean;
import formbean.impl.AddMenuFormBean;
import formbean.impl.AddRoleFormBean;
import formbean.impl.LoginFormBean;
import formbean.impl.UpdateRoleFormBean;
import formbean.impl.UpdateMenuFormBean;

import service.BussinessService;
import service.impl.BussinessServiceImpl;
import utils.FormBeanUtils;

public class Router extends HttpServlet {

	private BussinessService service = new BussinessServiceImpl();

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		
		System.out.println(action);

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
			HttpServletResponse response) {
		request.setAttribute("page", getPage(new MenuBean(), request));
		request.getRequestDispatcher("/manager/listAllMenus.jsp");
	}

	private void delMenus(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String[] ids = request.getParameterValues("ids");
		System.out.println(ids);
		if (ids != null && ids.length > 0) {
			service.delMenusByIds(ids);
			response.sendRedirect(request.getContextPath() + "/manager/listAllMenus.jsp");
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

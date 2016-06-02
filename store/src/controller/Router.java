package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import service.BussinessService;
import service.impl.BussinessServiceImpl;
import utils.FormBeanUtils;

import domain.MenuBean;
import domain.Page;
import exception.DaoException;
import formbean.impl.AddMenuFormBean;
import formbean.impl.ChangeMenuFormBean;

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
		if ("addMenu".equalsIgnoreCase(action)) {
			addMenu(request, response);
			return;
		}
		if ("delMenus".equalsIgnoreCase(action)) {
			delMenus(request, response);
			return;
		}
		if ("delMenu".equalsIgnoreCase(action)) {
			delMenu(request, response);
			return;
		}
		if ("changeMenu".equalsIgnoreCase(action)) {
			changeMenu(request, response);
			return;
		}
		if ("updateMenu".equalsIgnoreCase(action)) {
			updateMenu(request, response);
			return;
		}
	}

	private void changeMenu(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String id = request.getParameter("id");
		if (id != null && !id.trim().isEmpty()) {
			MenuBean menu = service.findMenuById(id);
			if (menu == null) {
				response.sendRedirect(request.getContextPath() + "/router?action=showAllMenus");
				return;
			}
			ChangeMenuFormBean formBean = new ChangeMenuFormBean();
			try {
				BeanUtils.copyProperties(formBean, menu);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/manager/changeMenu.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/router?action=showAllMenus");
		}
		
	}

	private void listAllMenus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("page", getPage(new MenuBean(), request));
		request.getRequestDispatcher("/manager/listAllMenus.jsp").forward(request, response);
	}
	
	private void addMenu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AddMenuFormBean formBean = null;
		try {
			formBean = FormBeanUtils.fill(request, AddMenuFormBean.class);
			if (formBean.validate() == true) {
				MenuBean menu = new MenuBean();
				BeanUtils.copyProperties(menu, formBean);
				service.addMenu(menu);
				response.sendRedirect(request.getContextPath() + "/router?action=listAllMenus");
			} else {
				request.setAttribute("formBean", formBean);
				request.getRequestDispatcher("/manager/addMenu.jsp").forward(request, response);
			}
		} catch (DaoException e) {
			e.printStackTrace();
			formBean.getMessages().put("result", "添加菜单失败，可能是菜单标题重复");
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/manager/addMenu.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

	private void delMenu(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		if (id != null && !id.trim().isEmpty()) service.delMenuById(id);
		response.sendRedirect(request.getContextPath() + "/router?action=listAllMenus");
	}

	
	private void delMenus(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String[] ids = request.getParameterValues("ids");
		if (ids != null && ids.length > 0) {
			for (String id : ids) {
				if (!id.trim().isEmpty()) {
					service.delRelationsByMenuId(id);
					service.delMenuById(id);
				}
			}
		}
		response.sendRedirect(request.getContextPath() + "/router?action=listAllMenus");
	}
	
	private void updateMenu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ChangeMenuFormBean formBean = null;
		try {
			formBean = FormBeanUtils.fill(request, ChangeMenuFormBean.class);
			if (formBean.validate() == true) {
				MenuBean menu = new MenuBean();
				BeanUtils.copyProperties(menu, formBean);
				service.updateMenu(menu);
				response.sendRedirect(request.getContextPath() + "/router?action=listAllMenus");
			} else {
				request.setAttribute("formBean", formBean);
				request.getRequestDispatcher("/manager/changeMenu.jsp").forward(request, response);
			}
		} catch (DaoException e) {
			e.printStackTrace();
			formBean.getMessages().put("result", "修改菜单失败，可能是菜单标题重复");
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/manager/changeMenu.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
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

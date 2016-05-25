package controller;

import java.awt.MenuBar;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import domain.MenuBean;
import exception.MenuDaoException;

import formbean.impl.AddMenuFormBean;

import service.BussinessService;
import service.BussinessServiceImpl;
import utils.FormBeanUtils;

public class Router extends HttpServlet {

	private final String viewPath = "/WEB-INF/pages/views/";
	
	private BussinessService service = new BussinessServiceImpl();

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");

		// 显示Jsp页面
		if ("show".equalsIgnoreCase(action)) {
			showAction(request, response);
			return;
		}
		
		if ("addMenu".equalsIgnoreCase(action)) {
			addMenuAction(request, response);
			return;
		}

	}

	private void addMenuAction(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		AddMenuFormBean formBean = null;
		try {
			// 获取FormBean
			formBean = FormBeanUtils.fill(request, AddMenuFormBean.class);
			// 测试FormBean是否有效
			// 有效
			if (formBean.validate()) {
				// 添加到数据库
				MenuBean menu = new MenuBean();
				BeanUtils.copyProperties(menu, formBean);
				service.addMenu(menu);
				response.sendRedirect(request.getContextPath() + "/router?action=show&view=mgrMenu");
			// 无效转发到添加页面显示错误信息
			} else {
				request.setAttribute("formBean", formBean);
				request.getRequestDispatcher("/WEB-INF/pages/views/mgrAddMenu.jsp").forward(request, response);
			}
		} catch (MenuDaoException e) {
			// 如果添加不成功转发到添加页面显示错误信息
			e.printStackTrace();
			formBean.getMessages().put("result", "菜单项重复");
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/WEB-INF/pages/views/mgrAddMenu.jsp").forward(request, response);
		} catch (Exception e) {
			// 出错跳转消息页面
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/router?action=show&view=messages");
		}
		
	}

	private void showAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String path = null;
		String view = request.getParameter("view");
		
		if ("mgrIndex".equalsIgnoreCase(view)) path = "/manager/index.jsp";
		if ("mgrMenu".equalsIgnoreCase(view)) {
			request.setAttribute("menus", service.findAllMenus());
			path = viewPath + "mgrMenu.jsp";
		}
		if ("mgrAddMenu".equalsIgnoreCase(view)) path = viewPath + "mgrAddMenu.jsp";
		if ("mgrRole".equalsIgnoreCase(view)) path = viewPath + "mgrRole.jsp";
		if ("mgrAccount".equalsIgnoreCase(view)) path = viewPath + "mgrAccount.jsp";
		
		request.getRequestDispatcher(path).forward(request, response);
	}

}

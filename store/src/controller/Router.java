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

	private BussinessService service = new BussinessServiceImpl();

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action == null)
			action = "index";

		if ("index".equalsIgnoreCase(action)) {
			return;
		}

		if ("addmenu".equalsIgnoreCase(action)) {
			addMenuAction(request, response);
			return;
		}

		if ("show".equalsIgnoreCase(action)) {
			showAction(request, response);
			return;
		}

	}

	private void addMenuAction(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		AddMenuFormBean formBean = null;
		try {
			// ��ȡFormBean
			formBean = FormBeanUtils.fill(request, AddMenuFormBean.class);
			// ����FormBean�Ƿ���Ч
			// ��Ч
			if (formBean.validate()) {
				// ��ӵ����ݿ�
				MenuBean menu = new MenuBean();
				BeanUtils.copyProperties(menu, formBean);
				service.addMenu(menu);
				response.sendRedirect(request.getContextPath() + "/servlet/router?action=show&view=bgmenu");
			// ��Чת�������ҳ����ʾ������Ϣ
			} else {
				request.setAttribute("formBean", formBean);
				request.getRequestDispatcher("/WEB-INF/pages/server/addMenu.jsp").forward(request, response);
			}
		} catch (MenuDaoException e) {
			// �����Ӳ��ɹ�ת�������ҳ����ʾ������Ϣ
			e.printStackTrace();
			formBean.getMessages().put("result", "�˵����ظ�");
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/WEB-INF/pages/server/addMenu.jsp").forward(request, response);
		} catch (Exception e) {
			// ������ת��Ϣҳ��
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/messages.jsp");
		}
		
	}

	private void showAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String path = null;
		String view = request.getParameter("view");
		if ("bgindex".equalsIgnoreCase(view)) {
			path = "/server/index.jsp";
		}
		if ("bgmenu".equalsIgnoreCase(view)) {
			request.setAttribute("menus", service.findAllMenus());
			path = "/WEB-INF/pages/server/menu.jsp";
		}
		if ("addmenu".equalsIgnoreCase(view)) {
			path = "/WEB-INF/pages/server/addMenu.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

}

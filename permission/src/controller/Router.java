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

		// ��ʾJspҳ��
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
		
		if ("mgrIndex".equalsIgnoreCase(view)) path = "/manager/index.jsp";
		if ("mgrMenu".equalsIgnoreCase(view)) {
			request.setAttribute("menus", service.findAllMenus());
			path = viewPath + "mgrMenu.jsp";
		}
		if ("mgrRole".equalsIgnoreCase(view)) path = viewPath + "mgrRole.jsp";
		if ("mgrAccount".equalsIgnoreCase(view)) path = viewPath + "mgrAccount.jsp";
		
		request.getRequestDispatcher(path).forward(request, response);
	}

}

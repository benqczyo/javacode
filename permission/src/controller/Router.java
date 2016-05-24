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

		if ("show".equalsIgnoreCase(action)) {
			showAction(request, response);
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
				response.sendRedirect(request.getContextPath() + "/servlet/router?action=show&view=bgmenu");
			// 无效转发到添加页面显示错误信息
			} else {
				request.setAttribute("formBean", formBean);
				request.getRequestDispatcher("/WEB-INF/pages/server/addMenu.jsp").forward(request, response);
			}
		} catch (MenuDaoException e) {
			// 如果添加不成功转发到添加页面显示错误信息
			e.printStackTrace();
			formBean.getMessages().put("result", "菜单项重复");
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/WEB-INF/pages/server/addMenu.jsp").forward(request, response);
		} catch (Exception e) {
			// 出错跳转消息页面
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/messages.jsp");
		}
		
	}

	private void showAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}

package com.benqcz.loginex.web.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.benqcz.loginex.domain.UserBean;
import com.benqcz.loginex.service.UserService;
import com.benqcz.loginex.service.impl.UserServiceImpl;
import com.benqcz.loginex.web.formbean.impl.LoginFormBean;
import com.benqcz.utils.FormBeanUtils;

public class Router extends HttpServlet {
	
	private UserService service = new UserServiceImpl();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if ("login".equalsIgnoreCase(action)) {
			doLogin(request, response);
			return;
		}
		
	}

	private void doLogin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String path = "/WEB-INF/pages/login.jsp";
		
		try {
			LoginFormBean formBean = FormBeanUtils.fillFormBean(LoginFormBean.class, request);
			if (formBean.validate()) {
				UserBean userBean = service.login(formBean.getName(), formBean.getPassword());
				if (userBean != null) {
					request.getSession().setAttribute("user", userBean);
					path = "/index.jsp";
				} else {
					formBean.getMessages().put("result", "错误的用户名或密码");
					request.setAttribute("formBean", formBean);
				}
			} else {
				request.setAttribute("formBean", formBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			path = "/WEB-INF/pages/message.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

}

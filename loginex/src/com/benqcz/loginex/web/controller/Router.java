package com.benqcz.loginex.web.controller;


import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.omg.CORBA.UserException;

import com.benqcz.loginex.domain.UserBean;
import com.benqcz.loginex.exception.UserExistedException;
import com.benqcz.loginex.service.UserService;
import com.benqcz.loginex.service.impl.UserServiceImpl;
import com.benqcz.loginex.web.formbean.impl.LoginFormBean;
import com.benqcz.loginex.web.formbean.impl.RegisterFormBean;
import com.benqcz.utils.FormBeanUtils;

public class Router extends HttpServlet {
	
	private UserService service = new UserServiceImpl();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if ("showLoginView".equalsIgnoreCase(action)) {
			request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
			return;
		}
		
		if ("showRegisterView".equalsIgnoreCase(action)) {
			request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
			return;
		}
		
		if ("doLogin".equalsIgnoreCase(action)) {
			doLogin(request, response);
			return;
		}
		
		if ("doRegister".equalsIgnoreCase(action)) {
			doRegister(request, response);
			return;
		}
		
		if ("doLogout".equalsIgnoreCase(action)) {
			request.getSession().removeAttribute("user");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
		throw new ServletException();
	}

	private void doLogin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String path = "/WEB-INF/pages/login.jsp";
		
		try {
			LoginFormBean formBean = FormBeanUtils.fillFormBean(LoginFormBean.class, request);
			if (formBean.validate()) {
				UserBean userBean = service.login(formBean.getName(), formBean.getPassword());
				if (userBean != null) {
					HttpSession session = request.getSession(); 
					session.setAttribute("user", userBean);
					if (formBean.getAuto().equalsIgnoreCase("on")) {
						Cookie cookie = new Cookie("JSESSIONID", session.getId());
						cookie.setPath(getServletContext().getContextPath());
						cookie.setMaxAge(60 * 10);
						response.addCookie(cookie);
					}
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
	
	private void doRegister(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String path = "/WEB-INF/pages/register.jsp";
		
		RegisterFormBean formBean = null;
		try {
			formBean = FormBeanUtils.fillFormBean(RegisterFormBean.class, request);
			if (formBean.validate()) {
				UserBean userBean = new UserBean();
				BeanUtils.copyProperties(userBean, formBean);
				userBean.setId(UUID.randomUUID().toString());
				if (service.register(userBean)) path = "/index.jsp";
			} else {
				request.setAttribute("formBean", formBean);
			}
		} catch (UserExistedException e) {
			formBean.getMessages().put("result", "用户已存在");
			request.setAttribute("formBean", formBean);
		} catch (Exception e) {
			e.printStackTrace();
			path = "/WEB-INF/pages/message.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);
		
	}

}

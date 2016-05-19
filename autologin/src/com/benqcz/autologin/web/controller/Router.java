package com.benqcz.autologin.web.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.benqcz.autologin.domain.UserBean;
import com.benqcz.autologin.service.UserService;
import com.benqcz.autologin.service.impl.UserServiceImpl;
import com.benqcz.autologin.web.formbean.impl.LoginFormBean;
import com.benqcz.utils.FormBeanUtils;

public class Router extends HttpServlet {
	
	private UserService service = new UserServiceImpl();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String action = req.getParameter("action");
		
		if (action.equalsIgnoreCase("show")) {
			doShow(req, res);
			return;
		}
		
		if (action.equalsIgnoreCase("login")) {
			doLogin(req, res);
			return;
		}
		
		if (action.equalsIgnoreCase("logout")) {
			doLogout(req, res);
			return;
		}
		
		res.sendRedirect(String.format("%s/%s", req.getContextPath(), "message.jsp"));
		
	}

	private void doLogout(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.getSession().removeAttribute("user");
		Cookie cookie = new Cookie("loginInfo", null);
		cookie.setPath(req.getContextPath());
		cookie.setMaxAge(0);
		res.addCookie(cookie);
		res.sendRedirect(String.format("%s/%s", req.getContextPath(), "index.jsp"));
	}

	private void doShow(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String view = req.getParameter("view");
		if (view.equalsIgnoreCase("login")) {
			res.sendRedirect(String.format("%s/%s", getServletContext().getContextPath(), "login.jsp"));
			return;
		}
	}
	
	private void doLogin(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		
		LoginFormBean formBean = FormBeanUtils.fillFormBean(LoginFormBean.class, req);
		if (formBean.validate()) {
			UserBean user = service.login(formBean.getName(), formBean.getPassword());
			if (user != null) {
				req.getSession().setAttribute("user", user);
				if (req.getParameter("auto") != null) {
					Cookie cookie = new Cookie("loginInfo", String.format("%s:%s", user.getName(), user.getPassword()));
					cookie.setPath(getServletContext().getContextPath());
					cookie.setMaxAge(Integer.MAX_VALUE);
					res.addCookie(cookie);
				} else {
					Cookie cookie = new Cookie("loginInfo", null);
					cookie.setPath(getServletContext().getContextPath());
					cookie.setMaxAge(0);
					res.addCookie(cookie);
				}
				res.sendRedirect(String.format("%s/%s", getServletContext().getContextPath(), "index.jsp"));
			} else {
				formBean.getMessages().put("result", "错误的用户名或密码");
				req.setAttribute("formBean", formBean);
				req.getRequestDispatcher("/login.jsp").forward(req, res);
			}
		} else {
			req.setAttribute("formBean", formBean);
			req.getRequestDispatcher("/login.jsp").forward(req, res);
		}
	}

}

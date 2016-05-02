package com.benqcz.fourm.web.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.benqcz.fourm.domain.UserBean;
import com.benqcz.fourm.exception.UserExistException;
import com.benqcz.fourm.service.UserService;
import com.benqcz.fourm.service.impl.UserServiceImpl;
import com.benqcz.fourm.utils.FormBeanUtils;
import com.benqcz.fourm.web.formbean.LoginFormBean;
import com.benqcz.fourm.web.formbean.RegisterFormBean;

public class CenterController extends HttpServlet {

	private UserService service = new UserServiceImpl();

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");

		String action = request.getParameter("action");
		
		if ("register".equalsIgnoreCase(action)) {
			register(request, response);
			return;
		}
		
		if ("login".equalsIgnoreCase(action)) {
			login(request, response);
			return;
		}
		
		if ("logout".equalsIgnoreCase(action)) {
			logout(request, response);
			return;
		}

	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		request.setAttribute("message", "ע���ɹ�,�������ת��ҳ...");
		response.setHeader("Refresh", String.format("3;url=%s", request.getContextPath()));
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "";
		LoginFormBean formBean = null;
		try {
			formBean = FormBeanUtils.fill(request, LoginFormBean.class);
			if (formBean.validate()) {
				UserBean userBean = service.login(formBean.getUsername(), formBean.getPassword());
				if (userBean != null) {
					request.getSession().setAttribute("user", userBean);
					request.setAttribute("message", "��½�ɹ�,�������ת��ҳ...");
					response.setHeader("Refresh", String.format("3;url=%s", request.getContextPath()));
					path = "/message.jsp";
				} else {
					formBean.getErrors().put("message", "������û���������");
					request.setAttribute("formBean", formBean);
					path = "/login.jsp";
				}
			} else {
				request.setAttribute("formBean", formBean);
				path = "/login.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("message", "��������æ�����Ժ�����...");
			path = "/message.jsp";
		}

		request.getRequestDispatcher(path).forward(request, response);
		
	}

	private void register(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String path = "";
		
		RegisterFormBean formBean = null;
		try {
			formBean = FormBeanUtils.fill(request, RegisterFormBean.class);
			if (formBean.validate()) {
				UserBean userBean = new UserBean();
				BeanUtils.copyProperties(userBean, formBean);
				service.register(userBean);
				request.setAttribute("message", "����ɹ�");
				path = "/message.jsp";
			} else {
				request.setAttribute("formBean", formBean);
				path = "/register.jsp";
			}
		} catch (UserExistException e) {
			formBean.getErrors().put("username", "�û����Ѵ���");
			request.setAttribute("formBean", formBean);
			path = "/register.jsp";
		} catch (Exception e) {
			request.setAttribute("message", "��������æ�����Ժ�����...");
			path = "/message.jsp";
		}

		request.getRequestDispatcher(path).forward(request, response);
	}

}

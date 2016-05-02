package com.benqcz.discuss.web.controller;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.benqcz.discuss.domain.User;
import com.benqcz.discuss.exception.UserExistException;
import com.benqcz.discuss.service.UserService;
import com.benqcz.discuss.service.impl.UserServiceImpl;
import com.benqcz.discuss.util.FormBeanUtil;
import com.benqcz.discuss.web.formbean.RegisterFormBean;
import com.sun.org.apache.commons.beanutils.BeanUtils;

public class RouterController extends HttpServlet {
	
	private UserService service = new UserServiceImpl();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String action = request.getParameter("action");
		
		if ("register".equalsIgnoreCase(action)) {
			register(request, response);
		}
		
	}

	private void register(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String result = "";
		
		RegisterFormBean registerFormBean = FormBeanUtil.fillFormBean(request, RegisterFormBean.class);
		if (!registerFormBean.validate()) {
			request.setAttribute("formBean", registerFormBean);
			result = "/register.jsp";
		} else {
			User user = new User();
			try {
				BeanUtils.copyProperties(user, registerFormBean);
				service.register(user);
				request.setAttribute("message", "注册成功");
				result = "/message.jsp";
			} catch (UserExistException e) {
				registerFormBean.getErrors().put("username", "用户名已存在");
				request.setAttribute("formBean", registerFormBean);
				result = "/register.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
			
		}
		
		request.getRequestDispatcher(result).forward(request, response);
		
	}
	
}

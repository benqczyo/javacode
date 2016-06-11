package controller;


import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import service.UserService;
import service.impl.UserServiceImpl;
import utils.FormBeanUtils;
import domain.UserBean;
import exception.UserExistException;
import exception.UserNotFoundException;
import formbean.impl.LoginFormBean;
import formbean.impl.RegisterFormBean;

public class Router extends HttpServlet {
	
	private UserService service = new UserServiceImpl();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		if ("kick".equalsIgnoreCase(action)) {
			kick(request, response);
			return;
		}
		response.sendRedirect(request.getContextPath() + "/messages.jsp");
	}

	private void kick(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		if (id != null && !id.trim().equals("")) {
			Map<UserBean, HttpSession> sessions = (Map<UserBean, HttpSession>) getServletContext().getAttribute("sessions");
			if (sessions != null) {
				for (Entry<UserBean, HttpSession> entry : sessions.entrySet()) {
					if (entry.getKey().getId().equalsIgnoreCase(id))
						entry.getValue().removeAttribute("user");
				}
				response.sendRedirect(request.getContextPath());
			}
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().removeAttribute("user");
		response.sendRedirect(request.getContextPath());
	}

	private void register(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获得formbean
		RegisterFormBean formBean = null;
		try {
			formBean = FormBeanUtils.fill(RegisterFormBean.class, request);
			//检查是否有效
			//有效添加
			if (formBean.isValidated()) {
				UserBean user = new UserBean();
				BeanUtils.copyProperties(user, formBean);
				service.register(user);
				response.sendRedirect(request.getContextPath());
			} else {
				request.setAttribute("formBean", formBean);
				request.getRequestDispatcher("/register.jsp").forward(request, response);
			}
		} catch (UserExistException e) {
			e.printStackTrace();
			formBean.getMessages().put("result", "重复的用户");
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获得formbean
		LoginFormBean formBean = null;
		try {
			formBean = FormBeanUtils.fill(LoginFormBean.class, request);
			//检查是否有效
			//有效添加
			if (formBean.isValidated()) {
				UserBean user = service.login(formBean.getName(), formBean.getPassword());
				request.getSession().setAttribute("user", user);
				response.sendRedirect(request.getContextPath());
			} else {
				request.setAttribute("formBean", formBean);
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			formBean.getMessages().put("result", "用户名或者密码错误");
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
	}
	
}

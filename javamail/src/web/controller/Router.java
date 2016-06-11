package web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import javax.security.auth.login.AccountException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import domain.AccountBean;
import exception.AccountActiveFailedException;
import exception.AccountExistException;
import exception.AccountInactiveException;
import exception.AccountNotFoundException;

import service.AccountService;
import service.impl.AccountServiceImpl;
import utils.FormBeanUtils;
import web.formbean.impl.LoginFormBean;
import web.formbean.impl.RegisterFormBean;

public class Router extends HttpServlet {

	private AccountService service = new AccountServiceImpl();

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
		if ("check".equalsIgnoreCase(action)) {
			check(request, response);
			return;
		}
		response.sendRedirect(request.getContextPath() + "/messages.jsp");
	}

	private void check(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String code = request.getParameter("code");
		if (code == null || code.trim().isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/messages.jsp");
			return;
		}
		try {
			service.enableAccount(code);
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/messages.jsp");
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath());
	}

	private void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LoginFormBean formBean = FormBeanUtils.fill(LoginFormBean.class, request);
		if (formBean == null) {
			response.sendRedirect(request.getContextPath() + "/messages.jsp");
			return;
		}
		if (!formBean.isValidated()) {
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		try {
			AccountBean account = service.login(formBean.getName(), formBean.getPassword());
			request.getSession().setAttribute("account", account);
			response.sendRedirect(request.getContextPath());
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
			formBean.getMessages().put("result", "’À∫≈ªÚ√‹¬Î¥ÌŒÛ");
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		} catch (AccountInactiveException e) {
			e.printStackTrace();
			formBean.getMessages().put("result", "’À∫≈√ª”–º§ªÓ");
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/messages.jsp");
		}
		
	}

	private void register(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RegisterFormBean formBean = FormBeanUtils.fill(RegisterFormBean.class,
				request);
		if (formBean == null) {
			response.sendRedirect(request.getContextPath() + "/messages.jsp");
			return;
		}
		if (!formBean.isValidated()) {
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
		try {
			AccountBean account = new AccountBean();
			BeanUtils.copyProperties(account, formBean);
			service.register(account);
			response.sendRedirect(request.getContextPath() + "/sendmail.jsp");
		} catch (AccountExistException e) {
			e.printStackTrace();
			formBean.getMessages().put("result", "’À∫≈“—¥Ê‘⁄");
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/messages.jsp");
		}
	}

}

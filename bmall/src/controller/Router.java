package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.soap.AddressingFeature;

import org.apache.commons.beanutils.BeanUtils;

import domain.impl.CategoryBean;
import exception.CategoryExistsException;
import exception.DeleteCategoryException;
import exception.InvalidIdException;
import formbean.impl.AddCategoryFormBean;

import service.Service;
import service.impl.ServiceImpl;
import utils.FormBeanUtils;

public class Router extends HttpServlet {
	
	private Service service = new ServiceImpl();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("listAllCategories".equalsIgnoreCase(action)) {
			listAllCategories(request, response);
			return;
		}
		if ("addCategory".equalsIgnoreCase(action)) {
			addCategory(request, response);
			return;
		}
		if ("delCategory".equalsIgnoreCase(action)) {
			delCategory(request, response);
			return;
		}
	}

	private void delCategory(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			service.delCategoryById(request.getParameter(request.getParameter("id")));
			response.sendRedirect(request.getContextPath() + "/router？action=listAllCategories");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/messages.jsp");
		}
	}

	private void addCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AddCategoryFormBean formBean = FormBeanUtils.fill(AddCategoryFormBean.class, request);
		if (formBean == null) {
			response.sendRedirect(request.getContextPath() + "/messages.jsp");
			return;
		}
		if (!formBean.isValidated()) {
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/manager/addCategory.jsp").forward(request, response);
			return;
		}
		try {
			CategoryBean category = new CategoryBean();
			BeanUtils.copyProperties(category, formBean);
			service.addCategory(category);
			response.sendRedirect(request.getContextPath() + "/router?action=listAllCategories");
		} catch (CategoryExistsException e) {
			e.printStackTrace();
			formBean.getMessages().put("result", "分类已存在");
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/manager/addCategory.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/messages.jsp");
		}
	}

	private void listAllCategories(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pageId = request.getParameter("pageId");
		ServletConfig config = getServletConfig();
		String recordsOfSinglePage = config.getInitParameter("recordsOfSinglePage");
		String buttonsOfSinglePage = config.getInitParameter("buttonsOfSinglePage");
		request.setAttribute("page", service.getPage(new CategoryBean(), recordsOfSinglePage, buttonsOfSinglePage, pageId));
		request.getRequestDispatcher("/manager/listAllCategories.jsp").forward(request, response);
	}
	
}

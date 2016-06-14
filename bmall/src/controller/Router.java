package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.soap.AddressingFeature;

import org.apache.commons.beanutils.BeanUtils;

import domain.impl.CategoryBean;
import exception.AddCategoryException;
import exception.DeleteCategoryException;
import exception.InvalidIdException;
import formbean.impl.AddCategoryFormBean;
import formbean.impl.UpdateCategoryFormBean;

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
		if ("showUpdateCategoryPage".equalsIgnoreCase(action)) {
			showUpdateCategoryPage(request, response);
			return;
		}
		if ("updateCategory".equalsIgnoreCase(action)) {
			updateCategory(request, response);
			return;
		}
	}

	private void showUpdateCategoryPage(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			CategoryBean category = service.findCategoryById(request.getParameter("id"));
			UpdateCategoryFormBean formBean = new UpdateCategoryFormBean();
			BeanUtils.copyProperties(formBean, category);
			request.setAttribute("formBean", formBean);	
			request.getRequestDispatcher("/manager/updateCategory.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/messages.jsp");
		}
	}

	private void updateCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UpdateCategoryFormBean formBean = FormBeanUtils.fill(UpdateCategoryFormBean.class, request);
		if (formBean == null) {
			response.sendRedirect(request.getContextPath() + "/messages.jsp");
			return;
		}
		if (!formBean.isValidated()) {
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/manager/updateCategory.jsp").forward(request, response);
			return;
		}
		try {
			CategoryBean category = new CategoryBean();
			BeanUtils.copyProperties(category, formBean);
			service.updateCategory(category);
			response.sendRedirect(request.getContextPath() + "/router?action=listAllCategories");
		} catch (AddCategoryException e) {
			e.printStackTrace();
			formBean.getMessages().put("result", "���·���ʧ��,���ܵ�ԭ���Ƿ����Ѵ���");
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/manager/addCategory.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/messages.jsp");
		}
		
	}

	private void delCategory(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			service.delCategoryById(request.getParameter("id"));
			response.sendRedirect(request.getContextPath() + "/router?action=listAllCategories");
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
		} catch (AddCategoryException e) {
			e.printStackTrace();
			formBean.getMessages().put("result", "��ӷ���ʧ��,���ܵ�ԭ���Ƿ����Ѵ���");
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

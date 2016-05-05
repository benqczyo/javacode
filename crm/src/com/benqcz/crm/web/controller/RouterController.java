package com.benqcz.crm.web.controller;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import com.benqcz.crm.domain.CustomerBean;
import com.benqcz.crm.service.CustomerService;
import com.benqcz.crm.service.impl.CustomerServiceImpl;
import com.benqcz.crm.utils.FormBeanUtils;
import com.benqcz.crm.web.formbean.AddFormBean;
import com.benqcz.crm.web.formbean.UpdateFormBean;
import com.sun.org.apache.commons.beanutils.PropertyUtils;

public class RouterController extends HttpServlet {
	
	private CustomerService service = new CustomerServiceImpl();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String action = request.getParameter("action");
		
		if (action == null) {
			showAllCustomer(request, response);
			return;
		}
		
		if ("showAddForm".equalsIgnoreCase(action)) {
			addCustomer(request, response);
			return;
		}
		
		if ("validateAddFormBean".equalsIgnoreCase(action)) {
			validateAddFormBean(request, response);
			return;
		}
		
		if ("showUpdateForm".equalsIgnoreCase(action)) {
			showUpdateCustomerForm(request, response);
			return;
		}
		
		if ("validateUpdateFormBean".equalsIgnoreCase(action)) {
			validateUpdateFormBean(request, response);
			return;
		}
		
		if ("delete".equalsIgnoreCase(action)) {
			deleteCustomer(request, response);
			return;
		}
		
		if ("deleteMutilCustomer".equalsIgnoreCase(action)) {
			deleteMutilCustomer(request, response);
			return;
		}
		
		request.setAttribute("message", "系统繁忙请稍后再试...");
		request.getRequestDispatcher("/WEB-INF/pages/message.jsp").forward(request, response);
	}

	private void deleteMutilCustomer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String[] ids = request.getParameterValues("id");
			if (ids != null && ids.length > 0)
				service.deleteMutilCustomer(ids);
			request.setAttribute("message", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "系统繁忙请稍后再试");
		}
		request.getRequestDispatcher("/WEB-INF/pages/message.jsp").forward(request, response);
	}

	private void deleteCustomer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			service.deleteCustomerById(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("message", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "系统繁忙请稍后再试...");
		}
		request.getRequestDispatcher("/WEB-INF/pages/message.jsp").forward(request, response);
	}

	private void validateUpdateFormBean(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String path = "";
		UpdateFormBean formBean = null;
		try {
			formBean = FormBeanUtils.fill(request, UpdateFormBean.class);
			if (!formBean.validate()) {
				request.setAttribute("formBean", formBean);
				path = "/WEB-INF/pages/updateCustomer.jsp";
			} else {
				CustomerBean customer = new CustomerBean();
				ConvertUtils.register(new DateLocaleConverter(), Date.class);
				BeanUtils.copyProperties(customer, formBean);
				service.updateCustomer(customer);
				request.setAttribute("message", "更新成功");
				path = "/WEB-INF/pages/message.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "系统繁忙请稍后再试...");
			path = "/WEB-INF/pages/message.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);
		
	}

	private void showUpdateCustomerForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/WEB-INF/pages/updateCustomer.jsp";
		try {
			CustomerBean customer = service.findCustomerById(Integer.parseInt(request.getParameter("id")));
			UpdateFormBean formBean = new UpdateFormBean();
			PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(CustomerBean.class);
			ConvertUtils.register(new DateLocaleConverter(), Date.class);
			for (PropertyDescriptor pd : pds) {
				String pName = pd.getName();
				if (!pName.equalsIgnoreCase("class"))
					BeanUtils.copyProperty(formBean, pName, pd.getReadMethod().invoke(customer, null));
			}
			request.setAttribute("formBean", formBean);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "系统繁忙请稍后再试...");
			path = "/WEB-INF/pages/message.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

	private void validateAddFormBean(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String path = "";
		AddFormBean formBean = null;
		try {
			formBean = FormBeanUtils.fill(request, AddFormBean.class);
			if (!formBean.validate()) {
				request.setAttribute("formBean", formBean);
				path = "/WEB-INF/pages/addCustomer.jsp";
			} else {
				CustomerBean customer = new CustomerBean();
				ConvertUtils.register(new DateLocaleConverter(), Date.class);
				BeanUtils.copyProperties(customer, formBean);
				service.addCustomer(customer);
				request.setAttribute("message", "添加成功");
				path = "/WEB-INF/pages/message.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "系统繁忙请稍后再试...");
			path = "/WEB-INF/pages/message.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

	private void addCustomer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/addCustomer.jsp").forward(request, response);
	}

	private void showAllCustomer(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		List<CustomerBean> customers = service.findCustomer();
		request.setAttribute("customers", customers);
		request.getRequestDispatcher("/WEB-INF/pages/showAllCustomers.jsp").forward(request, response);
		
	}

}

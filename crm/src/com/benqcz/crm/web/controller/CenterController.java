package com.benqcz.crm.web.controller;


import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.benqcz.crm.domain.CustomerBean;
import com.benqcz.crm.service.CustomerService;
import com.benqcz.crm.service.impl.CustomerServiceImpl;

public class CenterController extends HttpServlet {

	private CustomerService service = new CustomerServiceImpl();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));
		
		if ("list".equalsIgnoreCase(action)) {
			list(request, response);
			return;
		}
		
		
	}

	private void list(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		String path = "";
		Map<Integer, CustomerBean> customers = null;
		try {
			customers = service.findCustomer();
			if (customers != null) {
				request.setAttribute("customers", customers);
				path = "/WEB-INF/pages/list.jsp";
			} else {
				request.setAttribute("message", "还没有添加用户");
				path = "/WEB-INF/pages/message.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("error", "服务器繁忙,请稍后再试...");
			path = "/WEB-INF/pages/error.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);
	}
	
}

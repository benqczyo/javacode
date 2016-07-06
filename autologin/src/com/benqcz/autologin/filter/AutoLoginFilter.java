package com.benqcz.autologin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import sun.misc.BASE64Decoder;

import com.benqcz.autologin.dao.impl.UserDaoImpl;
import com.benqcz.autologin.domain.UserBean;

public class AutoLoginFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		if (session.getAttribute("user") == null) {
			Cookie[] cookies = req.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					String cookieName = cookie.getName();
					if (cookieName.equalsIgnoreCase("loginInfo")) {
						String[] params = cookie.getValue().split(":");
						String userName = new String(new BASE64Decoder().decodeBuffer(params[0]));
						UserBean user = new UserDaoImpl().findUserByName(userName);
						if (user != null && user.getPassword().equalsIgnoreCase(params[1])) {
							session.setAttribute("user", user);
						}
					}
				}
			}
		}
		filterChain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}

package com.benqcz.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class AntiHotlinking extends SimpleTagSupport {

	private String site;
	private String redirect;

	public void setSite(String site) {
		this.site = site;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	@Override
	public void doTag() throws JspException, IOException {
		PageContext pageContext = ((PageContext) getJspContext());
		HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
		HttpServletResponse res = (HttpServletResponse) pageContext.getResponse();
		String from = req.getHeader("Referer");
		if (from != null && !from.startsWith(site)) {
			res.sendRedirect(req.getContextPath() + redirect);
		} 
	}

}

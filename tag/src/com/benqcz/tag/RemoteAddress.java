package com.benqcz.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class RemoteAddress extends TagSupport {

	@Override
	public int doStartTag() throws JspException {
		try {
			pageContext.getOut().write(pageContext.getRequest().getRemoteAddr());
		} catch (IOException e) {
			throw new JspException(e);
		}
		return super.doStartTag();
	}

}

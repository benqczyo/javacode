package com.benqcz.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SimpleHide extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		throw new SkipPageException();
	}
	
}

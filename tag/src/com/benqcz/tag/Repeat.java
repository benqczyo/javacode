package com.benqcz.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.IterationTag;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class Repeat extends TagSupport {
	
	private int count;
	
	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int doAfterBody() throws JspException {
		return --count == 0 ? IterationTag.SKIP_BODY : IterationTag.EVAL_BODY_AGAIN;
	}

	@Override
	public int doStartTag() throws JspException {
		return Tag.EVAL_BODY_INCLUDE;
	}
	
	
	
	
}

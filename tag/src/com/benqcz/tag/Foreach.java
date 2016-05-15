package com.benqcz.tag;

import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.IterationTag;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class Foreach extends TagSupport {

	private Iterable items;
	private String var;
	private Iterator it;

	public Iterable getItems() {
		return items;
	}

	public void setItems(Iterable items) {
		this.items = items;
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	@Override
	public int doAfterBody() throws JspException {
		while (it.hasNext()) {
			pageContext.setAttribute(var, it.next());
			return IterationTag.EVAL_BODY_AGAIN;
		}
		pageContext.removeAttribute(var);
		return Tag.SKIP_BODY;
	}

	@Override
	public int doStartTag() throws JspException {
		it = items.iterator();
		if (it.hasNext()) {
			pageContext.setAttribute(var, it.next());
			return Tag.EVAL_BODY_INCLUDE;
		}
		return Tag.SKIP_BODY;
	}

}

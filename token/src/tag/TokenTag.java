package tag;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TokenTag extends SimpleTagSupport {
	
	@Override
	public void doTag() throws JspException, IOException {
		String token = UUID.randomUUID().toString();
		PageContext pageContext = (PageContext) getJspContext();
		pageContext.getSession().setAttribute("token", token);
		pageContext.getOut().write(token);
	}

}

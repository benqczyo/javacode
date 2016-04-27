package yo.benqczyo.bookstore.web.controller.main.Impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RouterController extends AbstractRouterController {
	
	private String parseAction(HttpServletRequest request) {
		String uri = request.getRequestURI();
		return uri.substring(uri.lastIndexOf('/') + 1, uri.lastIndexOf('.'));
	}	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			controllers.get(parseAction(request)).exec(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	
	}


}

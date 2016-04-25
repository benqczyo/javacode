package yo.benqczyo.bookstore.web.controller.fregment.book.Impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ClearBookControllerFregment extends AbstractBookControllerFregment {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.invalidate();
		
		Cookie cookie = new Cookie("JSESSIONID", null);
		cookie.setPath(request.getContextPath());
		cookie.setMaxAge(0);
		
		response.addCookie(cookie);
		
		response.sendRedirect(String.format("%s/%s", request.getContextPath(), "list.do"));
	
	}

}

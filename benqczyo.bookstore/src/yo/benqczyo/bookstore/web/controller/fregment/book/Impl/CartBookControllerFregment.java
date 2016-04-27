package yo.benqczyo.bookstore.web.controller.fregment.book.Impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CartBookControllerFregment extends AbstractBookControllerFregment {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String error = (String) session.getAttribute("error");
		if (error != null) {
			request.setAttribute("error", error);
			session.removeAttribute("error");
		}
		
		request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request, response);
		
	}

}

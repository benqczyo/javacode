package yo.benqczyo.bookstore.web.controller.fregment.book.Impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import yo.benqczyo.bookstore.domain.Cart;
import yo.benqczyo.bookstore.service.BookService;
import yo.benqczyo.bookstore.service.Impl.BookServiceImpl;

public class ChangeBookControllerFregment extends
		AbstractBookControllerFregment {
	
	private BookService bookService = new BookServiceImpl();
	
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart != null) {
			String id = request.getParameter("id");
			String newValue = request.getParameter("value");
			if (!bookService.change(cart, id, newValue)) 
				session.setAttribute("error", "请检查修改的购买数量");
		}

		response.sendRedirect(String.format("%s/%s", request.getContextPath(), "cart.do"));
		
	}
	
}

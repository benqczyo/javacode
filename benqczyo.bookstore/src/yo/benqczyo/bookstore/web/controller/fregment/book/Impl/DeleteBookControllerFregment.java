package yo.benqczyo.bookstore.web.controller.fregment.book.Impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import yo.benqczyo.bookstore.domain.Cart;
import yo.benqczyo.bookstore.service.BookService;
import yo.benqczyo.bookstore.service.Impl.BookServiceImpl;

public class DeleteBookControllerFregment extends
		AbstractBookControllerFregment {
	
	private BookService bookService = new BookServiceImpl();

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart != null) {
			try {
				bookService.delete(cart, request.getParameter("id"));
			} catch (Exception e) {
				session.setAttribute("error", "<script>alert('无法删除指定的项目');</script>");
			}
		}
		
		response.sendRedirect(String.format("%s/%s", request.getContextPath(), "cart.do"));
			
	}

}

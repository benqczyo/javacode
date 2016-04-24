package yo.benqczyo.bookstore.web.controller.fregment.book.Impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import yo.benqczyo.bookstore.domain.Book;
import yo.benqczyo.bookstore.domain.Cart;
import yo.benqczyo.bookstore.service.BookService;
import yo.benqczyo.bookstore.service.Impl.BookServiceImpl;

public class BuyBookControllerFregment extends AbstractBookControllerFregment {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(Integer.MAX_VALUE);
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) session.setAttribute("cart", new Cart());
		
		Book book = null;
		try {
			book = bookService.findBookById(Integer.parseInt(request.getParameter("id")));
			bookService.add2Cart((Cart) session.getAttribute("cart"), book);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
		Cookie cookie = new Cookie("JSESSSIONID", session.getId());
		cookie.setPath(request.getContextPath());
		cookie.setMaxAge(Integer.MAX_VALUE);
		response.addCookie(cookie);
		
		request.setAttribute("result", book);
		request.getRequestDispatcher("/WEB-INF/pages/result.jsp").forward(request, response);
	}

}

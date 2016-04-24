package yo.benqczyo.bookstore.web.controller.fregment.book.Impl;

import java.io.IOException;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yo.benqczyo.bookstore.domain.Book;
import yo.benqczyo.bookstore.domain.Cart;
import yo.benqczyo.bookstore.domain.CartItem;

public class CartBookControllerFregment extends AbstractBookControllerFregment {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request, response);
		
	}

}

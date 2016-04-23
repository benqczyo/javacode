package yo.benqczyo.bookstore.web.controller.fregment.book.Impl;

import java.io.IOException;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yo.benqczyo.bookstore.domain.Book;

public class ListBookControllerFregment extends AbstractBookControllerFregment {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setAttribute("allBooks", bookService.findAllBooks());
			request.getRequestDispatcher("/WEB-INF/pages/list.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
	}

}

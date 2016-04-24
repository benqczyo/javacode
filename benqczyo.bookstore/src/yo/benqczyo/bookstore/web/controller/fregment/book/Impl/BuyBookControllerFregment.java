package yo.benqczyo.bookstore.web.controller.fregment.book.Impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yo.benqczyo.bookstore.domain.Book;
import yo.benqczyo.bookstore.service.BookService;
import yo.benqczyo.bookstore.service.Impl.BookServiceImpl;

public class BuyBookControllerFregment extends AbstractBookControllerFregment {

	private BookService bookService = new BookServiceImpl();
	
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			request.setAttribute("book", bookService.findBookById(Integer.parseInt(request.getParameter("id"))));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
	}

}

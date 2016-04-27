package yo.benqczyo.bookstore.web.controller.fregment.book.Impl;

import yo.benqczyo.bookstore.service.BookService;
import yo.benqczyo.bookstore.service.Impl.BookServiceImpl;
import yo.benqczyo.bookstore.web.controller.fregment.book.BookControllerFregment;

public abstract class AbstractBookControllerFregment implements BookControllerFregment {
	protected BookService bookService = new BookServiceImpl();
}

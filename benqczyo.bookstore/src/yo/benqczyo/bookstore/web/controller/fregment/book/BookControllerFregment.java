package yo.benqczyo.bookstore.web.controller.fregment.book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BookControllerFregment {
	public abstract void exec(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException , IOException;
}

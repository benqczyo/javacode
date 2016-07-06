package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import configer.Constant;
import db.Book;
import db.BookDB;

public class Router extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));
	
		if (Constant.LIST_ACTION.equalsIgnoreCase(action)) {
			List<Book> historyBooks = null;
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie c : cookies) {
					if (Constant.HISTORY_ATTR.equalsIgnoreCase(c.getName())) {
						historyBooks = new ArrayList<Book>();
						for (String id : c.getValue().split(Constant.SEPARATOR))
							historyBooks.add(BookDB.findBookById(id));
						break;
					}
				}
			}
			
			request.setAttribute(Constant.HISTORY_ATTR, historyBooks);
			request.setAttribute(Constant.ALL_BOOKS_ATTR, new ArrayList<Book>(BookDB.findAllBooks().values()));
			request.getRequestDispatcher(Constant.LIST_PAGE).forward(request, response);
			return;
		}
		
		if (Constant.DETAIL_ACTION.equalsIgnoreCase(action)) {
			
			String id = request.getParameter(Constant.ID_ATTR);
			Book book = BookDB.findBookById(id);
			if (book == null) {
				response.sendRedirect(
						String.format("%s/%s",
								getServletContext().getContextPath(), 
								Constant.ERROR_PAGE));
				return;
			}
			
			StringBuffer sb = new StringBuffer();
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie c : cookies) {
					if (Constant.HISTORY_ATTR.equalsIgnoreCase(c.getName())) {
						LinkedList<String> history = new LinkedList<String>(Arrays.asList(c.getValue().split(Constant.SEPARATOR)));
						history.remove(id);
						if (history.size() + 1 > Constant.HISTORY_SIZE) history.removeLast();
						for (int i = 0; i < history.size(); i++) {
							if (i > 0) sb.append(Constant.SEPARATOR);
							sb.append(history.get(i));
						}
						break;
					}
				}
			}
			sb.insert(0, sb.length() == 0 ? id : String.format("%s%s", id, Constant.SEPARATOR));
			Cookie cookie = new Cookie(Constant.HISTORY_ATTR, sb.toString());
			cookie.setPath(getServletContext().getContextPath());
			cookie.setMaxAge(Integer.MAX_VALUE);
			response.addCookie(cookie);
			request.setAttribute(Constant.DETAIL_ATTR, book);
			request.getRequestDispatcher(Constant.DETAIL_PAGE).forward(request, response);
			return;
		}
		
		if (Constant.CLEAR_ACTION.equalsIgnoreCase(action)) {
			Cookie cookie = new Cookie(Constant.HISTORY_ATTR, null);
			String contextPath = getServletContext().getContextPath();
			cookie.setPath(contextPath);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			response.sendRedirect(String.format("%s/%s.do", contextPath, Constant.LIST_ACTION));
			return;
		}
		
		response.sendRedirect(
				String.format("%s/%s",
						getServletContext().getContextPath(), 
						Constant.ERROR_PAGE));
	}
	
}

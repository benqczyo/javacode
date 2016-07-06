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

import db.Book;
import db.BookDB;

public class Router extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));
	
		if ("list".equalsIgnoreCase(action)) {
			List<Book> historyBooks = null;
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie c : cookies) {
					if ("history".equalsIgnoreCase(c.getName())) {
						historyBooks = new ArrayList<Book>();
						for (String id : c.getValue().split("\\-"))
							historyBooks.add(BookDB.findBookById(id));
						break;
					}
				}
			}
			request.setAttribute("history", historyBooks);
			request.setAttribute("books", new ArrayList<Book>(BookDB.findAllBooks().values()));
			request.getRequestDispatcher("/list.jsp").forward(request, response);
			return;
		}
		
		if ("detail".equalsIgnoreCase(action)) {
			
			String id = request.getParameter("id");
			Book book = BookDB.findBookById(id);
			if (book == null) {
				response.sendRedirect(
						String.format("%s/%s",
								getServletContext().getContextPath(), 
								"error.jsp"));
				return;
			}
			
			StringBuffer sb = new StringBuffer();
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie c : cookies) {
					if ("history".equalsIgnoreCase(c.getName())) {
						LinkedList<String> history = new LinkedList<String>(Arrays.asList(c.getValue().split("\\-")));
						history.remove(id);
						if (history.size() + 1 > 3) history.removeLast();
						for (int i = 0; i < history.size(); i++) {
							if (i > 0) sb.append("\\-");
							sb.append(history.get(i));
						}
						break;
					}
				}
			}
			sb.insert(0, sb.length() == 0 ? id : String.format("%s%s", id, "\\-"));
			Cookie cookie = new Cookie("history", sb.toString());
			cookie.setPath(getServletContext().getContextPath());
			cookie.setMaxAge(Integer.MAX_VALUE);
			response.addCookie(cookie);
			request.setAttribute("book", book);
			request.getRequestDispatcher("/detail.jsp").forward(request, response);
			return;
		}
		
		if ("clear".equalsIgnoreCase(action)) {
			Cookie cookie = new Cookie("history", null);
			String contextPath = getServletContext().getContextPath();
			cookie.setPath(contextPath);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			response.sendRedirect(String.format("%s/%s.do", contextPath, "list"));
			return;
		}
		
		response.sendRedirect(
				String.format("%s/%s",
						getServletContext().getContextPath(), 
						"error.jsp"));
	}
	
}

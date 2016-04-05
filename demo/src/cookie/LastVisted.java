package cookie;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LastVisted extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		StringBuffer sb = new StringBuffer();
		Cookie[] cookies = request.getCookies();
		for (int i = 0; cookies != null && i < cookies.length; i++) {
			Cookie c = cookies[i];
			if ("lastvisted".equalsIgnoreCase(c.getName())) {
				sb.append("<strong>上次访问时间：")
					.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Long.parseLong(c.getValue()))))
					.append("</strong>");
			}
		}
		
		Cookie c = new Cookie("lastvisted", String.valueOf(System.currentTimeMillis()));
		c.setMaxAge(60);
		response.addCookie(c);
		out.print(sb.toString());
		
	}
	
}

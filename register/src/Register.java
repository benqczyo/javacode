
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Register extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String tokenFromRequest = request.getParameter("token");
		String tokenFromServer = (String) request.getSession().getAttribute("token");
		
		System.out.println(tokenFromRequest + ":" + tokenFromServer);
		
		if (tokenFromRequest != null && tokenFromServer != null 
				&& tokenFromRequest.equalsIgnoreCase(tokenFromServer)) {
			request.getSession().removeAttribute("token");
			System.out.println("正确的token值");
			return;
		}
		
		System.out.println("错误的token值");
		
	}
	
}

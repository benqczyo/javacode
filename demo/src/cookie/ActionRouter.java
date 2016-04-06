package cookie;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionRouter extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String uri = request.getRequestURI();
		String cmd = uri.substring(uri.lastIndexOf("/") + 1);
		
		if ("login".equalsIgnoreCase(cmd)) {
			
			Map<String, String[]> params = new HashMap<String, String[]>(request.getParameterMap());
			String keep = params.remove("keep")[0];
			User user;
			try {
				user = UserFactory.create(params);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException();
			}
			//User user = new User();
			//user.setName(params.get("name")[0]);
			//user.setPassword(params.get("password")[0]);
				
			if (UserDB.contains(user)) {
				Cookie cookieUser = new Cookie("name", URLEncoder.encode(user.getName(), "utf-8"));
				Cookie cookiePassword = new Cookie("password", URLEncoder.encode(user.getPassword(), "utf-8"));
				cookieUser.setPath(getServletContext().getContextPath());
				cookiePassword.setPath(getServletContext().getContextPath());
				if (keep != null) {
					cookieUser.setMaxAge(60 * 5);
					cookiePassword.setMaxAge(60 * 5);
				} else {
					cookieUser.setMaxAge(0); 
					cookiePassword.setMaxAge(0);
				}
				response.addCookie(cookieUser);
				response.addCookie(cookiePassword);
			}
		}
	}
	
}

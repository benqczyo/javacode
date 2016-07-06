package listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import domain.UserBean;

public class MonitorUserListener implements HttpSessionAttributeListener {

	private Object lock = new Object();

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		HttpSession session = event.getSession();
		Object object = session.getAttribute("user");
		if (object != null && object instanceof UserBean) {
			ServletContext context = session.getServletContext();
			UserBean user = (UserBean) object;
			Map<UserBean, HttpSession> sessions = (Map<UserBean, HttpSession>) context.getAttribute("sessions");
			if (sessions == null) {
				sessions = new HashMap<UserBean, HttpSession>();
				context.setAttribute("sessions", sessions);
			}
			synchronized (lock) {
				sessions.put(user, session);
			}
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		if (event.getName().equalsIgnoreCase("user")) {
			Map<UserBean, HttpSession> sessions = (Map<UserBean, HttpSession>) event.getSession().getServletContext().getAttribute("sessions");
			if (sessions != null) {
				Object key = event.getValue();
				HttpSession session = sessions.get(key);
				if (session != null) {
					session.invalidate();
					synchronized (lock ) {
						sessions.remove(key);
					}
				}
			}
				
		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub

	}

}

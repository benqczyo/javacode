import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class AutoCheckSessionListener implements HttpSessionListener,
		ServletContextListener, ServletRequestListener {
	
	private final long PERIOD_TIME = 1000 * 60 * 1;
	
	private List<HttpSession> sessions = new ArrayList<HttpSession>();
	private Map<String, Long> accessRecords = new HashMap<String, Long>();

	@Override
	public void contextInitialized(ServletContextEvent event) {
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				synchronized ("lock") {
					ListIterator<HttpSession> it = sessions.listIterator();
					while (it.hasNext()) {
						HttpSession session = it.next();
						if (System.currentTimeMillis() - session.getLastAccessedTime() > PERIOD_TIME) {
							session.invalidate();
							it.remove();
						}
					}
				}
			}
		}, 0, PERIOD_TIME);
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		System.out.println("新建会话" + session.getId());
		synchronized ("lock") {
			sessions.add(session);
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		System.out.println("退出会话" + event.getSession().getId());
	}

	@Override
	public void requestDestroyed(ServletRequestEvent event) {
		System.out.println(event.getServletRequest() + "退出");
	}

	@Override
	public void requestInitialized(ServletRequestEvent event) {
		HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
		System.out.println(request + "新建");
		String key = request.getRequestURI().replace(request.getContextPath(), "");
		Long val = accessRecords.get(key);
		val = (val == null) ? 1 : val + 1;
		accessRecords.put(key, val);
		System.out.println(accessRecords);
	}

}

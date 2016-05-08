package datasource;

import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

import javax.sql.DataSource;

public class DataSourceImpl implements DataSource {

	private static String driver;
	private static String url;
	private static String user;
	private static String password;

	private static LinkedList<Connection> pool = new LinkedList<Connection>();

	static {
		try {
			
			Properties props = new Properties();
			props.load(DataSourceImpl.class.getClassLoader().getResourceAsStream("source.cfg"));
			driver = props.getProperty("driver");
			url = props.getProperty("url");
			user = props.getProperty("user");
			password = props.getProperty("password");
			Class.forName(driver);

			for (int i = 0; i < 10; i++)
				pool.add(DriverManager.getConnection(url, user, password));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public synchronized Connection getConnection() throws SQLException {
		if (pool.size() <= 0) throw new SQLException("³Ø¿Ý½ß");
		final Connection conn = pool.remove();
		return (Connection) Proxy.newProxyInstance(conn.getClass().getClassLoader(), 
				new Class[] {Connection.class}, 
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) 
						throws Throwable {
						Object result = null;
						if ("close".equals(method.getName())) {
							pool.add(conn);
						} else {
							result = method.invoke(conn, args);
						}
						return result;
					}
				});
	}

	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		return null;
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return null;
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return 0;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {

	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}
}

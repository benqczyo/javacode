package com.benqcz.jdbc.entity;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

public class DataSourceImpl implements DataSource {
	
	private static final String CONFIG_FILE_NAME = "source.cfg";
	private static final int DEFAULT_POOL_SIZE = 10;
	
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	
	private static List<Connection> pool = new LinkedList<Connection>();
	
	static {
		Properties p = new Properties();
		try {
			p.load(DataSourceImpl.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME));
			url = p.getProperty("url");
			driver = p.getProperty("driver");
			user = p.getProperty("user");
			password = p.getProperty("password");
			Class.forName(driver);
			for (int i = 0; i < DEFAULT_POOL_SIZE; i++) 
				pool.add(DriverManager.getConnection(url, user, password));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public synchronized Connection getConnection() throws SQLException {
		if (pool.size() <= 0) throw new SQLException("³Ø¿Ý½ß");
		final Connection conn = pool.remove(0);
		Class clazz = conn.getClass();
		return (Connection) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				Object result = null;
				if ("close".equalsIgnoreCase(method.getName())) {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}

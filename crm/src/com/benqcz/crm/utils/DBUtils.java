package com.benqcz.crm.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public abstract class DBUtils {
	
	private static final String CONFIG_FILE_PATH = "db.cfg";
	
	private static String driver;
	private static String user;
	private static String password;
	private static String url;

	private static int initialSize;
	private static int maxActive;
	private static int maxIdle;
	private static int maxWait;
	
	private static BasicDataSource dataSource;
	private static ThreadLocal<Connection> connections;
	
	static {
		Properties configer = new Properties();
		try {
			configer.load(DBUtils.class.getClassLoader().getResourceAsStream(CONFIG_FILE_PATH));
			driver = configer.getProperty("driver");
			user = configer.getProperty("user");
			password = configer.getProperty("password");
			url = configer.getProperty("url");
			initialSize = Integer.parseInt(configer.getProperty("initial-size"));
			maxActive = Integer.parseInt(configer.getProperty("max-active"));
			maxIdle = Integer.parseInt(configer.getProperty("max-idle"));
			maxWait = Integer.parseInt(configer.getProperty("max-wait"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized static Connection open() {
		Connection result = null;
		
		if (dataSource == null) {
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName(driver);
			dataSource.setUrl(url);
			dataSource.setUsername(user);
			dataSource.setPassword(password);
			dataSource.setInitialSize(initialSize);
			dataSource.setMaxActive(maxActive);
			dataSource.setMaxIdle(maxIdle);
			dataSource.setMaxWait(maxWait);
		}
		
		if (connections == null) connections = new ThreadLocal<Connection>();
		if ((result = connections.get()) == null) {
			try {
				result = dataSource.getConnection();
				result.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			connections.set(result);
		}
		
		return result;
	}
	
	public static synchronized void close() {
		Connection conn = connections.get();
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				connections.remove();
				conn = null;
			}
	}
	
}

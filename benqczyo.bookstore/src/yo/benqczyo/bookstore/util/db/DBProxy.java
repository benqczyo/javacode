package yo.benqczyo.bookstore.util.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class DBProxy {
	
	protected static class DBConfig {

		static final String CONFIG_FILE_PATH = "dao.cfg";

		static String driver;
		static String user;
		static String password;
		static String url;

		static int initialSize;
		static int maxActive;
		static int maxIdle;
		static int maxWait;

		static {
			Properties configs = new Properties();
			try {
				configs.load(DBConfig.class.getClassLoader().getResourceAsStream(CONFIG_FILE_PATH));
				driver = configs.getProperty("driver");
				user = configs.getProperty("user");
				password = configs.getProperty("password");
				url = configs.getProperty("url");
				initialSize = Integer.parseInt(configs.getProperty("initial-size"));
				maxActive = Integer.parseInt(configs.getProperty("max-active"));
				maxIdle = Integer.parseInt(configs.getProperty("max-idle"));
				maxWait = Integer.parseInt(configs.getProperty("max-wait"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected static BasicDataSource dataSource;
	protected static ThreadLocal<Connection> connections;

	static {
		try {
			Class.forName(DBConfig.driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static synchronized Connection open() throws SQLException {

		if (dataSource == null) {
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName(DBConfig.driver);
			dataSource.setUrl(DBConfig.url);
			dataSource.setUsername(DBConfig.user);
			dataSource.setPassword(DBConfig.password);
			dataSource.setInitialSize(DBConfig.initialSize);
			dataSource.setMaxActive(DBConfig.maxActive);
			dataSource.setMaxIdle(DBConfig.maxIdle);
			dataSource.setMaxWait(DBConfig.maxWait);
		}

		Connection conn = null;
		if (connections == null) connections = new ThreadLocal<Connection>();
		if ((conn = connections.get()) == null) {
			try {
				conn = dataSource.getConnection();
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
			connections.set(conn);
		}
		return conn;
	}

	public static synchronized void close() throws SQLException {
		Connection conn = connections.get();
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} finally {
				connections.remove();
			}
	}
	
}

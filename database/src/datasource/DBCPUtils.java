package datasource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DBCPUtils {
	
	private static DataSource ds;
	private static ThreadLocal<Connection> connections;
	
	static {
		Properties p = new Properties();
		try {
			p.load(DBCPUtils.class.getClassLoader().getResourceAsStream("source.cfg"));
			ds = BasicDataSourceFactory.createDataSource(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection open() {
		Connection result = null;
		if (connections == null) connections = new ThreadLocal<Connection>();
		if ((result = connections.get()) == null) {
			try {
				result = ds.getConnection();
				result.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			connections.set(result);
		}
		return result;
	}
	
	public static void close() {
		Connection conn = connections.get();
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				connections.remove();
			}
	}
	
}

package datasource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Utils {
	
	private static ComboPooledDataSource ds;
	private static ThreadLocal<Connection> connections;
	
	static {
		try {
			ds = new ComboPooledDataSource();
			ds.setDriverClass("oracle.jdbc.OracleDriver");
			ds.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:orcl");
			ds.setUser("scott");
			ds.setPassword("sorry");
		} catch (PropertyVetoException e) {
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

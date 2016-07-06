package utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import exception.DBCPException;

public abstract class DBCPUtils {

	private static final String CONFIG_FILE = "dbcp.cfg";

	private static DataSource ds;
	private static ThreadLocal<Connection> conns;

	static {
		try {
			Properties props = new Properties();
			props.load(DBCPUtils.class.getClassLoader().getResourceAsStream(CONFIG_FILE));
			ds = BasicDataSourceFactory.createDataSource(props);
			conns = new ThreadLocal<Connection>();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Connection getSafeConnection() {
		Connection result = null;
		if ((result = conns.get()) == null)
			throw new DBCPException("没有连接到连接池");
		return result;
	}

	public static Connection open() {
		Connection result = null;
		if ((result = conns.get()) == null) {
			try {
				result = ds.getConnection();
				result.setAutoCommit(true);
				conns.set(result);
			} catch (Exception e) {
				throw new DBCPException(e);
			}
		}
		return result;
	}

	public static DataSource getDataSource() {
		return ds;
	}

	public static void startTransaction() {
		try {
			Connection conn = open();
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			throw new DBCPException(e);
		}
	}

	public static void rollback(Savepoint savepoint) {
		try {
			Connection conn = getSafeConnection();
			conn.rollback(savepoint);
		} catch (SQLException e) {
			throw new DBCPException(e);
		}
	}

	public static void rollback() {
		try {
			Connection conn = getSafeConnection();
			conn.rollback();
		} catch (SQLException e) {
			throw new DBCPException(e);
		}
	}

	public static void setSavePoint(String name) {
		try {
			Connection conn = getSafeConnection();
			conn.setSavepoint(name);
		} catch (SQLException e) {
			throw new DBCPException(e);
		}
	}

	public static void setSavePoint() {
		try {
			Connection conn = getSafeConnection();
			conn.setSavepoint();
		} catch (SQLException e) {
			throw new DBCPException(e);
		}
	}

	public static void commit() {
		try {
			Connection conn = getSafeConnection();
			conn.commit();
		} catch (SQLException e) {
			throw new DBCPException(e);
		}
	}

	public static void close() {
		Connection conn = conns.get();
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				throw new DBCPException(e);
			} finally {
				conns.remove();
			}
		}

	}

}

package com.benqcz.tool.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Utils {
	
	private static ComboPooledDataSource ds = new ComboPooledDataSource();
	private static ThreadLocal<Connection> connections;
	
	public static DataSource getDataSource() {
		return ds;
	}
	
	public static Connection open() {
		Connection result = null;
		if (connections == null) connections = new ThreadLocal<Connection>();
		if ((result = connections.get()) == null) {
			try {
				result = ds.getConnection();
				result.setAutoCommit(true);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			connections.set(result);
		}
		return result;
	}
	
	public static void startTransaction() {
		try {
			open().setAutoCommit(true);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void rollback(Savepoint savepoint) {
		try {
			open().rollback(savepoint);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void rollback() {
		try {
			open().rollback();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void setSavePoint(String name) {
		try {
			open().setSavepoint(name);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void setSavePoint() {
		try {
			open().setSavepoint();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void close() {
		Connection conn = connections.get();
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				connections.remove();
			}
	} 

}

package com.benqcz.utils.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

import javax.sql.DataSource;

import com.benqcz.utils.exception.C3P0UtilsException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * C3P0数据库池工具
 * @author benqcz
 * @version 1.0
 */
public class C3P0Utils {
	
	private static ComboPooledDataSource ds = new ComboPooledDataSource();
	private static ThreadLocal<Connection> connections;
	
	/**
	 * 返回数据库源
	 * @return 数据库源对象
	 */
	public static DataSource getDataSource() {
		return ds;
	}
	
	/**
	 * 返回数据库链接
	 * @return 数据库链接对象
	 */
	public static Connection open() {
		Connection result = null;
		if (connections == null) connections = new ThreadLocal<Connection>();
		if ((result = connections.get()) == null) {
			try {
				result = ds.getConnection();
				result.setAutoCommit(true);
			} catch (SQLException e) {
				throw new C3P0UtilsException(e);
			}
			connections.set(result);
		}
		return result;
	}
	
	/**
	 * 开启事务
	 */
	public static void startTransaction() {
		try {
			open().setAutoCommit(false);
		} catch (SQLException e) {
			throw new C3P0UtilsException(e);
		}
	}
	
	/**
	 * 事务回滚
	 */
	public static void rollback() {
		try {
			open().rollback();
		} catch (SQLException e) {
			throw new C3P0UtilsException(e);
		}
	}
	
	/**
	 * 事务回滚到保存点
	 * @param savepoint 保存点
	 */
	public static void rollback(Savepoint savepoint) {
		try {
			open().rollback(savepoint);
		} catch (SQLException e) {
			throw new C3P0UtilsException(e);
		}
	}
	
	/**
	 * 事务提交
	 */
	public static void commit() {
		try {
			open().commit();
		} catch (SQLException e) {
			throw new C3P0UtilsException(e);
		}
	}
	
	/**
	 * 关闭链接
	 */
	public static void close() {
		Connection conn = connections.get();
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				throw new C3P0UtilsException(e);
			} finally {
				connections.remove();
			}
	} 

}


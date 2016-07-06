package com.benqcz.utils.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

import javax.sql.DataSource;

import com.benqcz.utils.exception.C3P0UtilsException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * C3P0���ݿ�ع���
 * @author benqcz
 * @version 1.0
 */
public class C3P0Utils {
	
	private static ComboPooledDataSource ds = new ComboPooledDataSource();
	private static ThreadLocal<Connection> connections;
	
	/**
	 * �������ݿ�Դ
	 * @return ���ݿ�Դ����
	 */
	public static DataSource getDataSource() {
		return ds;
	}
	
	/**
	 * �������ݿ�����
	 * @return ���ݿ����Ӷ���
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
	 * ��������
	 */
	public static void startTransaction() {
		try {
			open().setAutoCommit(false);
		} catch (SQLException e) {
			throw new C3P0UtilsException(e);
		}
	}
	
	/**
	 * ����ع�
	 */
	public static void rollback() {
		try {
			open().rollback();
		} catch (SQLException e) {
			throw new C3P0UtilsException(e);
		}
	}
	
	/**
	 * ����ع��������
	 * @param savepoint �����
	 */
	public static void rollback(Savepoint savepoint) {
		try {
			open().rollback(savepoint);
		} catch (SQLException e) {
			throw new C3P0UtilsException(e);
		}
	}
	
	/**
	 * �����ύ
	 */
	public static void commit() {
		try {
			open().commit();
		} catch (SQLException e) {
			throw new C3P0UtilsException(e);
		}
	}
	
	/**
	 * �ر�����
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


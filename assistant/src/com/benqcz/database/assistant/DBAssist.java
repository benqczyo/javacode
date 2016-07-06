package com.benqcz.database.assistant;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.benqcz.database.assistant.exception.AssistantException;
import com.benqcz.database.assistant.handler.ResultSetHandler;

public class DBAssist {
	
	private DataSource ds;
	private ThreadLocal<Connection> connections;
	
	public DBAssist(DataSource ds) {
		this.ds = ds;
	}
	
	private Connection open() {
		Connection result = null;
		if (connections == null) connections = new ThreadLocal<Connection>();
		if ((result = connections.get()) == null) {
			try {
				result = ds.getConnection();
				result.setAutoCommit(true);
			} catch (Exception e) {
				throw new AssistantException(e);
			}
			connections.set(result);
		}
		return result;
	}
	
	
	public int update(String sql, Object[] params) {
		int result = -1;
		try {
			Connection conn = open();
			PreparedStatement st = conn.prepareStatement(sql);
			int numOfPlaceHolders = st.getParameterMetaData().getParameterCount();
			if (numOfPlaceHolders > 0 && (params == null || params.length != numOfPlaceHolders))
				throw new IllegalArgumentException("the number of parameters does not match"); 
			if (numOfPlaceHolders == 0 && params != null)
				throw new IllegalArgumentException("it is not need to transfer the parameters");
			for (int i = 1; i <= numOfPlaceHolders; i++)
				st.setObject(i, params[i - 1]);
			result = st.executeUpdate();
		} catch (Exception e) {
			throw new AssistantException(e);
		} finally {
			close();
		}
		return result;
	}
	
	public Object query(String sql, Object[] params, ResultSetHandler handler) {
		Object result = null;
		try {
			Connection conn = open();
			PreparedStatement st = conn.prepareStatement(sql);
			int numOfPlaceHolders = st.getParameterMetaData().getParameterCount();
			if (numOfPlaceHolders > 0 && (params == null || params.length != numOfPlaceHolders))
				throw new IllegalArgumentException("the number of parameters does not match");
			if (numOfPlaceHolders == 0 && params != null)
				throw new IllegalArgumentException("it is not need to transfer the parameters"); 
			for (int i = 1; i <= numOfPlaceHolders; i++)
				st.setObject(i, params[i - 1]);
			result = handler.handle(st.executeQuery());
		} catch (Exception e) {
			throw new AssistantException(e);
		} finally {
			close();
		}
		return result;
	}
	
	private void close() {
		Connection conn = connections.get();
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				throw new AssistantException(e);
			} finally {
				connections.remove();
			}
	} 
	
}

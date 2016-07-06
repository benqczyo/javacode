package com.benqcz.jdbc.entity;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DataSourceConnection extends DefaultDataSourceConnection {
	
	private Connection innerConnection;
	private List<Connection> pool;

	public DataSourceConnection(List<Connection> pool, Connection innerConnection) {
		super(innerConnection);
		this.innerConnection = innerConnection;
		this.pool = pool;
	}

	@Override
	public void close() throws SQLException {
		pool.add(innerConnection);
		System.out.println("回收的链接：" + innerConnection);
	}
	
}
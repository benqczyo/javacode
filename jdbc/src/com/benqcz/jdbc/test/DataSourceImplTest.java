package com.benqcz.jdbc.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;

import com.benqcz.jdbc.entity.DataSourceImpl;

public class DataSourceImplTest {

	@Test(expected=java.sql.SQLException.class)
	public void testGetConnection() throws SQLException {
		DataSource ds = new DataSourceImpl();
		for (int i = 0; i < 100; i++) {
			ds.getConnection();
		}
	}

}

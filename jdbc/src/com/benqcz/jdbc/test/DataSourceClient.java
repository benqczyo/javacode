package com.benqcz.jdbc.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.benqcz.jdbc.entity.DataSourceImpl;

public class DataSourceClient {

	public static void main(String[] args) {
		DataSource ds = new DataSourceImpl();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM emp");
			while (rs.next()) {
				System.out.println(String.format("<%d>%s", rs.getInt("empno"), rs.getString("ename")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			/*if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (st != null)
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}*/
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
}

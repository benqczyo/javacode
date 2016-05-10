package com.benqcz.tool.db.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.benqcz.tool.db.C3P0Utils;

public class Client {

	public static void main(String[] args) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = C3P0Utils.open();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM emp");
			System.out.println(conn.getMetaData());
			while (rs.next()) {
				System.out.println(String.format("<%d>%s", rs.getInt("empno"),
						rs.getString("ename")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			C3P0Utils.close();
		}
	}

}

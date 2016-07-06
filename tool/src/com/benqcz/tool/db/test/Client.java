package com.benqcz.tool.db.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.benqcz.tool.db.C3P0Utils;
import com.benqcz.tool.db.DBAssist;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Client {

	public static void main(String[] args) {
		DBAssist assist = new DBAssist(new ComboPooledDataSource());
		try {
			assist.update("insert into assist_test (num) values (?)", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

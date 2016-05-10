package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.benqcz.database.assistant.DBAssist;
import com.benqcz.database.assistant.handler.BeanHandler;
import com.mchange.v2.c3p0.ComboPooledDataSource;



public class Client {

	public static void main(String[] args) {
		DBAssist assist = new DBAssist(new ComboPooledDataSource());
		try {
			NumUnit result = (NumUnit) assist.query("select * from assist_test where num = 3", null, new BeanHandler(NumUnit.class));
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

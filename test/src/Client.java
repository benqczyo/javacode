

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import com.benqcz.database.assistant.DBAssist;
import com.benqcz.database.assistant.handler.BeanHandler;
import com.benqcz.database.assistant.handler.BeanListHandler;
import com.mchange.v2.c3p0.ComboPooledDataSource;



public class Client {

	public static void main(String[] args) {
		DBAssist assist = new DBAssist(new ComboPooledDataSource());
		try {
			List result = (List) assist.query("select * from assist_test", null, new BeanListHandler(NumUnit.class));
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

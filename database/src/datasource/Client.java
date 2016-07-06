package datasource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class Client {
	private static DataSource ds = new DataSourceImpl();
	public static void main(String[] args) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM emp");
			while (rs.next()) {
				System.out.println(String.format("<%d>%s", rs.getInt("empno"), rs.getString("ename")));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(st!=null){
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl extends AbstractUserDAOImpl {
	
	protected static final String FIND_USER_BY_ID = "SELECT * FROM userdemo WHERE id = ?";
	protected static final String FIND_USER_BY_NAME = "SELECT * FROM userdemo WHERE name = ?";

	@Override
	protected User findUserById(Connection conn, int id) throws Exception {
		PreparedStatement st = conn.prepareStatement(FIND_USER_BY_ID);
		st.setInt(1, id);
		ResultSet rs = st.executeQuery();
		User result = UserFactory.create(rs);
		rs.close();
		st.close();
		return result;
	}
	
	@Override
	protected User findUserByName(Connection conn, String name)
			throws Exception {
		PreparedStatement st = conn.prepareStatement(FIND_USER_BY_NAME);
		st.setString(1, name);
		ResultSet rs = st.executeQuery();
		User result = UserFactory.create(rs);
		rs.close();
		st.close();
		return result;
	}

	public static void main(String[] args) {
		UserDAOImpl dao = new UserDAOImpl();
		try {
			System.out.println(dao.findUserByName("jack"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}

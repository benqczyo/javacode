package dao;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractUserDAOImpl implements UserDAO {

	@Override
	public User findUserById(int id) throws Exception {
		try {
			return findUserById(DBProxy.open(), id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBProxy.close();
		}
	}

	protected abstract User findUserById(Connection conn, int id) throws Exception;

	@Override
	public User findUserByName(String name) throws Exception {
		try {
			return findUserByName(DBProxy.open(), name);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBProxy.close();
		}
	}
	
	protected abstract User findUserByName(Connection conn, String name) throws Exception;
}

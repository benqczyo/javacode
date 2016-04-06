package dao;

import java.sql.SQLException;

public interface UserDAO {
	public abstract User findUserById(int id) throws Exception;
	public abstract User findUserByName(String name) throws Exception;
}

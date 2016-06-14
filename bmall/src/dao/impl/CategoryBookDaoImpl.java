package dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import utils.DBCPUtils;

import dao.CategoryBookDao;
import exception.DaoException;

public class CategoryBookDaoImpl implements CategoryBookDao {
	
	private static final String DEL_RELATIONSHIP_BY_CATEGORY_ID = "UPDATE book SET categoryId = '00000000000000000000000000000000' WHERE categoryId = ?";
	
	private QueryRunner qr = new QueryRunner();

	@Override
	public boolean delRelationshipByCategoryId(String id) {
		try {
			return qr.update(DBCPUtils.open(), DEL_RELATIONSHIP_BY_CATEGORY_ID, id) >= 0 ;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public boolean delRelationshipByCategoryName(String name) {
		return false;
	}

}

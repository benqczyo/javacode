package com.benqcz.ikanke.dao.impl;

import java.sql.SQLException;

import com.benqcz.ikanke.dao.AbstractDao;
import com.benqcz.ikanke.dao.CategoryBookDao;
import com.benqcz.ikanke.exception.DaoException;
import com.benqcz.ikanke.utils.DBCPUtils;

public class CategoryBookDaoImpl extends AbstractDao implements CategoryBookDao {
	
	private static final String DEL_RELATIONSHIP_BY_CATEGORY_ID = "UPDATE book SET categoryId = '' WHERE categoryId = ?";
	
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

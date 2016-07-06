package com.benqcz.ikanke.factory;

import com.benqcz.ikanke.dao.AbstractDao;
import com.benqcz.ikanke.exception.DaoFactoryException;

public abstract class DaoFactory {
	public static <T extends AbstractDao> T getDaoInstance(Class<T> clazz) {
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			throw new DaoFactoryException(e);
		}
	}
}

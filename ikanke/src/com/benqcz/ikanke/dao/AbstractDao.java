package com.benqcz.ikanke.dao;

import java.sql.Connection;

import org.apache.commons.dbutils.QueryRunner;

import com.benqcz.ikanke.utils.DBCPUtils;

public abstract class AbstractDao {
	protected QueryRunner qr = new QueryRunner();
}

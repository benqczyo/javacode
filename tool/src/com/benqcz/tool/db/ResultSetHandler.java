package com.benqcz.tool.db;

import java.sql.ResultSet;

public interface ResultSetHandler {
	Object parse(ResultSet rs);
}

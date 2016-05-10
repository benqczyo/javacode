package com.benqcz.database.assistant.handler;


import java.sql.ResultSet;

public interface ResultSetHandler {
	Object handle(ResultSet rs);
}

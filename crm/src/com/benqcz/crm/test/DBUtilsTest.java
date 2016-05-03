package com.benqcz.crm.test;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

import com.benqcz.crm.utils.DBUtils;

public class DBUtilsTest {

	@Test
	public void testOpen() {
		Connection conn = DBUtils.open();
		assertNotNull(conn);
		System.out.println(conn);
	}

	@Test
	public void testClose() {
		fail("Not yet implemented");
	}

}

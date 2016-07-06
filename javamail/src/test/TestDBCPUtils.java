package test;

import static org.junit.Assert.*;

import org.junit.Test;

import utils.DBCPUtils;

public class TestDBCPUtils {

	@Test
	public void testOpen() {
		System.out.println(DBCPUtils.open());
	}

	@Test
	public void testStartTransaction() {
		DBCPUtils.startTransaction();
	}

	@Test
	public void testRollbackSavepoint() {
		DBCPUtils.rollback();
	}

	@Test
	public void testRollback() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetSavePointString() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetSavePoint() {
		fail("Not yet implemented");
	}

	@Test
	public void testClose() {
		fail("Not yet implemented");
	}

}

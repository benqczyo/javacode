package test;

import static org.junit.Assert.*;

import org.junit.Test;

import service.Service;
import service.impl.ServiceImpl;

public class TestServiceImpl {
	
	private Service service = new ServiceImpl();

	@Test
	public void testAddCategory() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelCategoryById() {
		service.delCategoryById("dd8f4460d8334d8888df78fcb1909d45");
	}

	@Test
	public void testDelCategoryByName() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateCategory() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllCategory() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindCategoryById() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindCategoryByName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPage() {
		fail("Not yet implemented");
	}

}

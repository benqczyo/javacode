package test;

import static org.junit.Assert.*;

import java.util.UUID;

import junit.framework.Assert;

import org.junit.Test;

import dao.CategoryDao;
import dao.impl.CategoryDaoImpl;
import domain.impl.CategoryBean;

public class TestCategoryDao {
	
	private CategoryDao dao = new CategoryDaoImpl();

	@Test
	public void testAddCategory() {
		CategoryBean category = new CategoryBean();
		category.setId(UUID.randomUUID().toString());
		category.setName("�����ѧ");
		category.setDescription("�������Ҵ���");
		Assert.assertTrue(dao.addCategory(category));
	}

	@Test
	public void testDelCategoryById() {
		Assert.assertTrue(dao.delCategoryById("e1537261-de3a-4778-9ba4-e88443394e8e"));
	}

	@Test
	public void testDelCategoryByName() {
		Assert.assertTrue(dao.delCategoryByName("�����ѧ"));
	}

	@Test
	public void testUpdateCategory() {
		CategoryBean category = new CategoryBean();
		category.setId("8dd87369-ad9c-4c73-ac0c-0206b12e64eb");
		category.setName("������ѧ");
		category.setDescription("������ѧ���Ҵ���");
		Assert.assertTrue(dao.updateCategory(category));
	}

	@Test
	public void testFindAllCategory() {
		Assert.assertNotNull(dao.findAllCategory());
	}

	@Test
	public void testFindCategoryById() {
		Assert.assertNotNull(dao.findCategoryById("8dd87369-ad9c-4c73-ac0c-0206b12e64eb"));
	}

	@Test
	public void testFindCategoryByName() {
		Assert.assertNotNull(dao.findCategoryByName("������ѧ"));
	}

}

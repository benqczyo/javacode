package test;

import java.util.List;
import java.util.UUID;

import junit.framework.Assert;
import junit.framework.TestCase;
import dao.MenuDao;
import dao.impl.MenuDaoImpl;
import domain.MenuBean;

public class MenuDaoImplTest extends TestCase {
	
	private MenuDao dao = new MenuDaoImpl();

	public void testAddMenu() {
		MenuBean menu = new MenuBean();
		menu.setId(UUID.randomUUID().toString());
		menu.setTitle("TEST-4");
		menu.setUri("/router?action=test");
		menu.setDescription("TEST-4");
		dao.addMenu(menu);
	}

	public void testDelMenuById() {
		Assert.assertEquals(true, dao.delMenuById("5f629a14-d2a1-4b2d-85f3-7279cfe9c3d7"));
	}

	public void testFindAllMenus() {
		List<MenuBean> menus = null;
		assertNotNull(menus = dao.findAllMenus());
		System.out.println(menus);
	}

	public void testFindMenuById() {
		MenuBean menu = null;
		assertNotNull(menu = dao.findMenuById("7bc359ee-50f6-471a-9df7-91bc3eb2f5b6"));
		System.out.println(menu);
	}

	public void testUpdateMenu() {
		MenuBean menu = new MenuBean();
		menu.setId("7bc359ee-50f6-471a-9df7-91bc3eb2f5b6");
		menu.setTitle("≤‚ ‘");
		menu.setUri("/router?action=test");
		menu.setDescription("can you see it");
		dao.updateMenu(menu);
	}
	
	public void testFindMenusByRange() {
		List<MenuBean> menus = null;
		assertNotNull(menus = dao.findMenusByRange(1, 100));
		System.out.println(menus);
	}

}

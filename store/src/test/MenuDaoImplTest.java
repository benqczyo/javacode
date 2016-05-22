package test;

import java.util.UUID;

import dao.MenuDao;
import dao.impl.MenuDaoImpl;
import domain.MenuBean;
import junit.framework.TestCase;

public class MenuDaoImplTest extends TestCase {
	
	private MenuDao dao = new MenuDaoImpl();

	public void testAddMenu() {
		MenuBean menu = new MenuBean();
		menu.setId(UUID.randomUUID().toString());
		menu.setTitle("test");
		menu.setUri("/router?action=test");
		menu.setDescription("just test");
		dao.addMenu(menu);
	}

	public void testDelMenuById() {
		fail("Not yet implemented");
	}

	public void testFindAllMenus() {
		fail("Not yet implemented");
	}

	public void testFindMenuById() {
		fail("Not yet implemented");
	}

	public void testUpdateMenu() {
		fail("Not yet implemented");
	}

}

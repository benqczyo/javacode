package service;

import java.util.List;
import java.util.UUID;

import dao.MenuDao;
import dao.impl.MenuDaoImpl;
import domain.MenuBean;

public class BussinessServiceImpl implements BussinessService {
	
	private MenuDao dao = new MenuDaoImpl();

	@Override
	public boolean addMenu(MenuBean menu) {
		menu.setId(UUID.randomUUID().toString());
		return dao.addMenu(menu);
	}

	@Override
	public boolean delMenuById(String id) {
		return dao.delMenuById(id);
	}

	@Override
	public List<MenuBean> findAllMenus() {
		return dao.findAllMenus();
	}

	@Override
	public MenuBean findMenuById(String id) {
		return dao.findMenuById(id);
	}

	@Override
	public boolean updateMenu(MenuBean menu) {
		return dao.updateMenu(menu);
	}

}

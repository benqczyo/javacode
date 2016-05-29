package domain;

import java.io.Serializable;
import java.util.List;

public class RoleBean implements Serializable {

	private String id;
	private String name;
	private String description;
	private List<MenuBean> menus;

	public RoleBean() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<MenuBean> getMenus() {
		return menus;
	}

	public void setMenus(List<MenuBean> menus) {
		this.menus = menus;
	}

	@Override
	public String toString() {
		return String.format(
				"RoleBean [description=%s, id=%s, menus=%s, name=%s]",
				description, id, menus, name);
	}

}

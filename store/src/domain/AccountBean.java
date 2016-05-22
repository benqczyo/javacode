package domain;

import java.io.Serializable;
import java.util.List;

public class AccountBean implements Serializable {

	private String id;
	private String name;
	private String password;
	private List<RoleBean> roles;

	public AccountBean() {}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<RoleBean> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleBean> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return String.format(
				"AccountBean [id=%s, name=%s, password=%s, roles=%s]", id,
				name, password, roles);
	}

}

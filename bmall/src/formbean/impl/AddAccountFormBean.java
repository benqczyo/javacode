package formbean.impl;

public class AddAccountFormBean extends AbstractFormBean {

	private String id;
	private String name;
	private String password;

	public AddAccountFormBean() {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean validate() {
		if (name == null || name.trim().equals("")) {
			messages.put("name", "«Î ‰»Î’À∫≈√˚≥∆");
		}

		if (password == null || password.trim().equals("")) {
			messages.put("password", "«Î ‰»Î’À∫≈√‹¬Î");
		}

		return messages.isEmpty();
	}

	@Override
	public String toString() {
		return String.format(
				"AddAccountFormBean [id=%s, name=%s, password=%s]", id, name,
				password);
	}

}

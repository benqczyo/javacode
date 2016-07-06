package domain;

public class AccountBean extends AbstractBean {
	
	private String id;
	private String name;
	private String password;
	private String email;
	private String code;
	private boolean actived;
	
	public AccountBean() {
		
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isActived() {
		return actived;
	}

	public void setActived(boolean actived) {
		this.actived = actived;
	}

	@Override
	public String toString() {
		return String
				.format("AccountBean [id=%s, name=%s, password=%s, email=%s, code=%s, actived=%s]",
						id, name, password, email, code, actived);
	}
	
}

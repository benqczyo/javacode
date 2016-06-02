package formbean.impl;


public class ChangeMenuFormBean extends AbstractFormBean {
	
	private String id;
	private String title;
	private String uri;
	private String description;

	public ChangeMenuFormBean() {}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean validate() {
		if (title == null || title.trim().equals("")) {
			messages.put("title", "请输入菜单标题");
		}
		if (uri == null || uri.trim().equals("")) {
			messages.put("uri", "请输入菜单地址");
		} else if (!uri.startsWith("/")) {
			messages.put("uri", "菜单地址必须是相对地址以/开头");
		}
		if (description == null || description.trim().equals("")) {
			messages.put("description", "请输入描述");
		}
		return messages.isEmpty();
	}
	
	@Override
	public String toString() {
		return String.format(
				"AddMenuFormBean [description=%s, id=%s, title=%s, uri=%s]",
				description, id, title, uri);
	}

}

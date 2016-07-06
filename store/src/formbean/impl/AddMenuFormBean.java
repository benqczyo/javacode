package formbean.impl;


public class AddMenuFormBean extends AbstractFormBean {
	
	private String id;
	private String title;
	private String uri;
	private String description;

	public AddMenuFormBean() {}
	
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
			messages.put("title", "������˵�����");
		}
		if (uri == null || uri.trim().equals("")) {
			messages.put("uri", "������˵���ַ");
		} else if (!uri.startsWith("/")) {
			messages.put("uri", "�˵���ַ��������Ե�ַ��/��ͷ");
		}
		if (description == null || description.trim().equals("")) {
			messages.put("description", "����������");
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

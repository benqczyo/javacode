package domain;

import java.io.Serializable;

public class MenuBean implements Serializable {
	
	private String id;
	private String title;
	private String uri;
	private String description;
	
	public MenuBean() {}

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

	@Override
	public String toString() {
		return String.format(
				"MenuBean [description=%s, id=%s, title=%s, uri=%s]",
				description, id, title, uri);
	}

}

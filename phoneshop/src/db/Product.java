package db;

public class Product {

	protected int id;
	protected String name;
	protected String description;
	protected double price;
	protected String image;
	
	public Product() {
		this(-1, null, null, -1, null);
	}
	
	public Product(int id, String name, String description, double price, String image) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.image = image;
	}
	
	public Product(Product p) {
		this.id = p.id;
		this.name = p.name;
		this.description = p.description;
		this.price = p.price;
		this.image = p.image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Product [description=" + description + ", id=" + id
				+ ", image=" + image + ", name=" + name + ", price=" + price
				+ "]";
	}
	
}

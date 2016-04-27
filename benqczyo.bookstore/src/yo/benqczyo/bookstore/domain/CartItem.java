package yo.benqczyo.bookstore.domain;

public class CartItem {
	
	private Book book;
	private int count;
	
	public CartItem(Book book) {
		this.book = book;
		this.count = 1;
	}

	public Book getBook() {
		return book;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getPrice() {
		return book.getPrice() * count;
	}

	@Override
	public String toString() {
		return "CartItem [book=" + book + ", count=" + count + "]";
	}
	
}

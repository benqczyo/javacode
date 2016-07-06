package strategy;

public class Ship implements Comparable<Ship> {

	private String name;
	private int length;
	private int capacity;

	private Comparator<Ship> comparator;

	public Ship() {
		this.comparator = new CapacityComparator();
	}

	public Ship(String name, int length, int capacity) {
		this();
		this.name = name;
		this.length = length;
		this.capacity = capacity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Comparator<Ship> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<Ship> comparator) {
		this.comparator = comparator;
	}

	@Override
	public int comparaTo(Ship o) {
		return comparator.compare(this, o);
	}

	@Override
	public String toString() {
		return String.format(
				"Ship [capacity=%s, comparator=%s, length=%s, name=%s]",
				capacity, comparator, length, name);
	}

}

package strategy;

public class CapacityComparator implements Comparator<Ship> {

	@Override
	public int compare(Ship o1, Ship o2) {
		return o1.getCapacity() == o2.getCapacity() ? 0 : o1.getCapacity() > o2.getCapacity() ? 1 : -1;
	}

}

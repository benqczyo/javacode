package strategy;

public class LengthComparator implements Comparator<Ship> {

	@Override
	public int compare(Ship o1, Ship o2) {
		return o1.getLength() == o2.getLength() ? 0 : o1.getLength() > o2.getLength() ? 1 : -1;
	}

}

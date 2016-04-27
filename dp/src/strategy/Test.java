package strategy;

public class Test {
	public static void main(String[] args) {
		Ship s1 = new Ship("banama", 100, 200);
		Ship s2 = new Ship("dream", 150, 160);
		System.out.println(s1.comparaTo(s2));
		s1.setComparator(new LengthComparator());
		System.out.println(s1.comparaTo(s2));
	}
}

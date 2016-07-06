package proxy;

public class Timer2 extends Tank {

	@Override
	public void move() {
		long startTime = System.currentTimeMillis();
		super.move();
		System.out.println("time:" + (System.currentTimeMillis() - startTime) / 1000);
		System.out.println("tank stop");
	}
	
}

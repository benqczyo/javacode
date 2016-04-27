package server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import observer.IObserver;
import subject.ISubject;
import customer.WeatherCustomer;

public class WeatherDataCenter implements ISubject {
	
	private List<IObserver> observers;

	public float getTemperature() {
		return new Random().nextFloat();
	}

	public float getHumidity() {
		return new Random().nextFloat();
	}

	public float getPressure() {
		return new Random().nextFloat();
	}

	@Override
	public void notifyAllObservers() {
		for (IObserver item : observers)
			item.update(getTemperature(), getHumidity(), getPressure());
	}
	
	@Override
	public void register(IObserver o) {
		if (observers == null) observers = new ArrayList<IObserver>();
		observers.add(o);

	}

	@Override
	public void remove(IObserver o) {
		if (observers != null) observers.remove(o);
	}
	
	public static void main(String[] args) {
		final ISubject center = new WeatherDataCenter();
		new WeatherCustomer(center);
		new WeatherCustomer(center);
		new WeatherCustomer(center);
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				center.notifyAllObservers();
			}
		}, 0, 3000);
	}

}

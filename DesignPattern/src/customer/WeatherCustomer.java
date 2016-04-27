package customer;

import observer.IObserver;
import subject.ISubject;
import display.IDisplay;

public class WeatherCustomer implements IDisplay, IObserver {
	
	private float temperature;
	private float huminity;
	private float pressure;
	
	private ISubject subject;
	
	public WeatherCustomer(ISubject subject) {
		this.subject = subject;
		this.subject.register(this);
	}

	@Override
	public void display() {
		System.out.println(String.format("客户：%s\n温度：%s\n湿度：%s\n压力：%s\n", hashCode(), temperature, huminity, pressure));
	}

	@Override
	public void update(float temperature, float huminity, float pressure) {
		this.temperature = temperature;
		this.huminity = huminity;
		this.pressure = pressure;
		display();
	}

}

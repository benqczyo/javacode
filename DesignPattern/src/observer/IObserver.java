package observer;

public interface IObserver {
	public abstract void update(float temperature, float huminity, float pressure);
}

package subject;

import observer.IObserver;

public interface ISubject {
	public abstract void register(IObserver o);
	public abstract void remove(IObserver o);
	public abstract void notifyAllObservers();
}

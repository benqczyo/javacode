package proxy;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;

public class Client {
	public static void main(String[] args) throws Exception {
		Moveable m = (Moveable) Proxy2.getProxyInstance(Connection.class);
		m.move();
	}
}

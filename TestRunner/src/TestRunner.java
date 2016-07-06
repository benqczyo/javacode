import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestRunner {

	public static void test(Class clazz) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			InstantiationException {
		Method[] methods = clazz.getMethods();
		for (Method m : methods) {
			if (m.isAnnotationPresent(MyTest.class))
				m.invoke(clazz.newInstance(), null);
		}
	}

	public static void main(String[] args) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			InstantiationException {
		test(TestUnit.class);
	}

}

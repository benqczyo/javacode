package proxy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Iterator;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

public abstract class Proxy {
	public static Object getProxyInstance() throws IOException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException, SecurityException, NoSuchMethodException,
			IllegalArgumentException, InvocationTargetException {
		String code = "package proxy;"
				+ "import java.util.Random;"
				+ "public class TankTimer implements Moveable {"
				+ "private Moveable innerObject;"
				+

				"public TankTimer(Moveable innerObject) {"
				+ "this.innerObject = innerObject;"
				+ "}"
				+ "@Override\n"
				+ "public void move() {"
				+ "long startTime = System.currentTimeMillis();"
				+ "innerObject.move();"
				+ "try {"
				+ "Thread.sleep(new Random().nextInt(10000));"
				+ "} catch (InterruptedException e) {"
				+ "e.printStackTrace();"
				+ "}"
				+ "System.out.println(\"time:\" + (System.currentTimeMillis() - startTime) / 1000);"
				+ "System.out.println(\"tank stop\");" + "}" +

				"}";

		File f = new File(System.getProperty("user.dir")
				+ "/src/proxy/TankTimer.java");
		FileWriter fw = new FileWriter(f);
		fw.write(code);
		fw.close();
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fmgr = compiler.getStandardFileManager(null,
				null, null);
		Iterable units = fmgr.getJavaFileObjects(f);
		CompilationTask task = compiler.getTask(null, fmgr, null, null, null,
				units);
		task.call();
		fmgr.close();
		URL[] urls = new URL[] { new URL("file:/"
				+ System.getProperty("user.dir") + "/src") };
		URLClassLoader loader = new URLClassLoader(urls);
		Class clazz = loader.loadClass("proxy.TankTimer");
		Constructor constructor = clazz.getConstructor(Moveable.class);
		return constructor.newInstance(new Tank());
	}

	public static void main(String[] args) throws IOException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException, SecurityException,
			IllegalArgumentException, NoSuchMethodException,
			InvocationTargetException {
		
	}
}

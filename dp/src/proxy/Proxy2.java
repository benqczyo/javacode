package proxy;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Proxy2 {

	private static final String CLASS_NAME = "ProxyTemp";
	private static final String LEFT_BRACE = "{";
	private static final String RIGHT_BRACE = "}";
	private static final String LEFT_QUOTE = "(";
	private static final String Right_QUOTE = ")";
	private static final String RN = "\n";

	public static Object getProxyInstance(Class clazz) throws Exception {
		StringBuffer code = new StringBuffer();
		String className = clazz.getName();
		String packageName = clazz.getPackage().getName();
		String packageDeclaration = String.format("package %s;\n", packageName);
		String classDeclarationBegin = String.format("public class %s implements %s %s\n", CLASS_NAME, clazz.getName(), LEFT_BRACE);
		String classDeclarationEnd = RIGHT_BRACE;
		StringBuffer methodDeclarations = new StringBuffer();
		for (Method method : clazz.getMethods()) {
			String methodOverrideFlag = "@Override\n";
			String methodModifer = String.format("%s ", "public");
			String methodReturnType = String.format("%s ", method.getReturnType().getName());
			Class[] methodParameters = method.getParameterTypes();
			StringBuffer methodParametersDeclarations = new StringBuffer();
			methodParametersDeclarations.append(String.format("%s", LEFT_QUOTE));
			for (int i = 0; i < methodParameters.length; i++) {
				String paramType = methodParameters[i].getName();
				System.out.println(methodParameters[i]);
				methodParametersDeclarations.append(String.format("%s arg%d", paramType, i)).append(i == methodParameters.length - 1 ? "" : ", ");
			}
			methodParametersDeclarations.append(Right_QUOTE);
			StringBuffer methodExceptionDeclarations = new StringBuffer();
			Class[] methodException = method.getExceptionTypes();
			for (int i = 0; i < methodException.length; i++) {
				String methodExceptionStr = methodException[i].getName();
				methodExceptionDeclarations.append(methodExceptionStr).append(i == methodException.length - 1 ? " " : "");
			}
			if (methodExceptionDeclarations.length() > 0) methodExceptionDeclarations.insert(0, "throws ");
			//String fieldsDeclarations = String.format("", args)
			String methodDeclarationBegin = String.format("%s%s%s%s %s%s\n", methodModifer, methodReturnType, method.getName(), methodParametersDeclarations, methodExceptionDeclarations, LEFT_BRACE);
			String methodDeclarationEnd = String.format("%s\n", RIGHT_BRACE);
			String methodDeclaration = String.format("\t%s\t%s\t\t%s\n\t%s\n", methodOverrideFlag, methodDeclarationBegin, ";", methodDeclarationEnd);
			methodDeclarations.append(methodDeclaration);
		}
		code.append(packageDeclaration).append(classDeclarationBegin).append(methodDeclarations).append(classDeclarationEnd);
		
		File f = new File(String.format("%s\\src\\%s\\%s.java", System.getProperty("user.dir"), Proxy2.class.getPackage().getName(), CLASS_NAME));
		FileWriter fw = new FileWriter(f);
		fw.write(code.toString());
		fw.close();
		return null;

	}
}

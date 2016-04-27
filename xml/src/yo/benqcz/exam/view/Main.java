package yo.benqcz.exam.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import yo.benqcz.exam.domain.Student;
import yo.benqcz.exam.service.Service;
import yo.benqcz.exam.service.impl.ServiceImpl;

public class Main {
	
	private static Scanner input = new Scanner(System.in);
	private static Map<String, Method> ops = new HashMap<String, Method>();
	private static Service service = new ServiceImpl();
	
	static {
		Method[] methods = Main.class.getDeclaredMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			if (!methodName.equalsIgnoreCase("main")) {
				ops.put(methodName, method);
			}
		}
	}
	
	private static void add() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Student student = new Student();
		System.out.print("输入sid:");
		try {
			student.setSid(br.readLine());
			System.out.print("输入name:");
			student.setName(br.readLine());
			System.out.print("输入location:");
			student.setLocation(br.readLine());
			System.out.print("输入eid:");
			student.setEid(br.readLine());
			System.out.print("输入grade:");
			student.setGrade(br.readLine());
			service.addStudent(student);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		int option = -1;
		for (;;) {
			do {
				System.out.println("------XML DataBase------");
				System.out.println(service.findAllStudents());
				System.out.println("1)增加");
				System.out.println("2)删除");
				System.out.println("3)修改");
				System.out.println("4)退出");
				option = input.nextInt();
			} while (option < 0 || option > 4);
			
			String cmd = null;
			switch (option) {
			case 1:
				cmd = "add";
				break;
			case 2:	
				cmd = "delete";
				break;
			case 3:
				cmd = "update";
				break;
			case 4:
				return;
			}
			
			try {
				ops.get(cmd).invoke(null, null);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
}

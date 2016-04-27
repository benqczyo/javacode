package yo.benqcz.xmldb.view.impl;

import java.util.Map.Entry;

import yo.benqcz.xmldb.domain.Student;
import yo.benqcz.xmldb.view.AbstractView;

public class MainView extends AbstractView {
	
	@Override
	public void exec() throws Exception {
		
		int option = -1;
		
		do {
			System.out.println("------XML���ݿ�ʾ��------");
			for (Entry<String, Student> item : service.findAllStudents().entrySet()) {
				System.out.println(item.getValue());
			}
			System.out.println("1.��ѯ");
			System.out.println("2.����");
			System.out.println("3.ɾ��");
			System.out.println("0.�˳�");
			System.out.print("������ѡ� ");
			option = input.nextInt();
		} while (option < 0 || option > 3);
		
		switch (option) {
		case 1:
			new QueryView().exec();
			break;
		case 2:
			new addView().exec();
			break;
		case 3:
			break;
		case 0:
			break;
		}
		
		service.save();
	}
	
	public static void main(String[] args) throws Exception {
		new MainView().exec();
	}

}

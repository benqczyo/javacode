package yo.benqcz.xmldb.view.impl;

import yo.benqcz.xmldb.domain.Student;
import yo.benqcz.xmldb.view.AbstractView;

public class addView extends AbstractView {

	@Override
	public void exec() throws Exception {
		Student s = new Student();
		System.out.print("������ID��");
		s.setSid(input.nextLine());
		System.out.print("������������");
		s.setName(input.nextLine());
		System.out.print("�����������");
		s.setLocation(input.nextLine());
		System.out.print("������γ�ID��");
		s.setEid(input.nextLine());
		System.out.print("������ɼ���");
		s.setGrade(input.nextLine());
		service.addStudent(s);
	}

}

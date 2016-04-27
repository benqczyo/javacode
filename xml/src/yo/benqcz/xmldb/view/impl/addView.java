package yo.benqcz.xmldb.view.impl;

import yo.benqcz.xmldb.domain.Student;
import yo.benqcz.xmldb.view.AbstractView;

public class addView extends AbstractView {

	@Override
	public void exec() throws Exception {
		Student s = new Student();
		System.out.print("请输入ID：");
		s.setSid(input.nextLine());
		System.out.print("请输入姓名：");
		s.setName(input.nextLine());
		System.out.print("请输入地区：");
		s.setLocation(input.nextLine());
		System.out.print("请输入课程ID：");
		s.setEid(input.nextLine());
		System.out.print("请输入成绩：");
		s.setGrade(input.nextLine());
		service.addStudent(s);
	}

}

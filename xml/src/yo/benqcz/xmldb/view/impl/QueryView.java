package yo.benqcz.xmldb.view.impl;

import yo.benqcz.xmldb.view.AbstractView;

public class QueryView extends AbstractView {

	@Override
	public void exec() throws Exception {
		System.out.print("����ID�Ų�ѯ��");
		System.out.println(service.findStudentById(input.nextLine()));
		
	}

}

package yo.benqcz.xmldb.view.impl;

import yo.benqcz.xmldb.view.AbstractView;

public class QueryView extends AbstractView {

	@Override
	public void exec() throws Exception {
		System.out.print(" ‰»ÎID∫≈≤È—Ø£∫");
		System.out.println(service.findStudentById(input.nextLine()));
		
	}

}

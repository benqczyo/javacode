package yo.benqcz.xmldb.view;

import java.util.Scanner;

import yo.benqcz.xmldb.service.Service;
import yo.benqcz.xmldb.service.impl.ServiceImpl;

public abstract class AbstractView implements View {
	
	protected Scanner input = new Scanner(System.in);
	
	protected static Service service = new ServiceImpl();

}

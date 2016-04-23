package yo.benqczyo.bookstore.web.controller.main.Impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import yo.benqczyo.bookstore.web.controller.fregment.book.BookControllerFregment;
import yo.benqczyo.bookstore.web.controller.main.MainController;

public abstract class AbstractRouterController extends HttpServlet implements MainController {

	protected static final String SERVER_CONFIG = "/WEB-INF/server.xml";

	protected Map<String, BookControllerFregment> controllers = new HashMap<String, BookControllerFregment>();

	/**
	 * ½âÎöserver.xml×¢²á¿ØÖÆÆ÷Æ¬¶Î
	 * @throws DocumentException
	 */
	@Override
	public void register() throws Exception {
		SAXReader reader =  new SAXReader();
		Document document = reader.read(new File(getServletContext().getRealPath(SERVER_CONFIG)));
		Element root = document.getRootElement();
		List<Element> fregments = root.element("fregments").elements();
		for (Element fregment : fregments) {
			String className = fregment.element("fregment-class").getTextTrim();
			String name = fregment.element("fregment-name").getTextTrim();
			Class c = Class.forName(className);
			controllers.put(name, (BookControllerFregment) c.newInstance());
		}
	}

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			register();
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}

package filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.dom4j.Document;
import org.dom4j.Node;

import com.benqcz.utils.XmlUtils;

public class DirtyWordFilter implements Filter {
	
	private final String DIRTY_WORDS_FILE = "dirtywords.xml";
	
	private final Map<String, String> dirtywords = new HashMap<String, String>();
	
	private class DirtyWordFilterRequest extends HttpServletRequestWrapper {

		public DirtyWordFilterRequest(HttpServletRequest request) {
			super(request);
		}

		@Override
		public String getParameter(String name) {
			String result = super.getParameter(name);
			if (result != null) {
				for (Entry<String, String> item : dirtywords.entrySet()) {
					result = result.replaceAll(item.getKey(), item.getValue());
				}
			}
			return result;
		}
		
	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		filterChain.doFilter(new DirtyWordFilterRequest((HttpServletRequest) request), response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String path = DirtyWordFilter.class.getClassLoader().getResource(DIRTY_WORDS_FILE).getPath();
		Document doc = XmlUtils.getUtilInstance(path, "UTF-8").open();
		List<Node> nodes = doc.selectNodes("//word");
		if (nodes != null) {
			for (Node node : nodes) {
				dirtywords.put(node.valueOf("@src"), node.valueOf("@dst"));
			}
		}
	}

}

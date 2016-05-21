package com.benqcz.filter.filter;

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

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import com.benqcz.utils.XmlUtils;

public class DirtyWordFilter implements Filter {
	
	private final String DIRTY_WORDS_FILE = "dirtywords.xml";
	
	private Map<String, String> dirtywords = new HashMap<String, String>();

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		for (Entry<String, String> item : dirtywords.entrySet()) {
			System.out.println(item.getKey() + "=" + item.getValue());
		}
		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String path = DirtyWordFilter.class.getClassLoader().getResource(DIRTY_WORDS_FILE).getPath();
		Document doc = XmlUtils.getUtilInstance(path, "UTF-8").open();
		List<Node> nodes = doc.selectNodes("//word");
		if (nodes != null) {
			for (Node node : nodes) {
				dirtywords.put(node.valueOf("//@src"), node.valueOf("//@dst"));
			}
		}
	}

}

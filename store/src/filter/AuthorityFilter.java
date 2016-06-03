package filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.BussinessService;
import service.impl.BussinessServiceImpl;

import dao.RoleDao;
import dao.RoleMenuDao;
import dao.impl.RoleDaoImpl;
import dao.impl.RoleMenuDaoImpl;
import domain.AccountBean;
import domain.MenuBean;
import domain.Page;
import domain.RoleBean;

public class AuthorityFilter implements Filter {
	
	private RoleDao dao = new RoleDaoImpl();

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		AccountBean account = (AccountBean) session.getAttribute("account");
		if (account == null) {
			res.sendRedirect(req.getContextPath() + "/front/login.jsp");
			return;
		}
		List<RoleBean> roles = account.getRoles();
		if (roles != null) {
			Set<MenuBean> menus = new HashSet<MenuBean>();
			for (RoleBean role : roles) {
				for (MenuBean menu : dao.findRoleById(role.getId()).getMenus())
					menus.add(menu);
			}
			boolean isMatched = false;
			String uri = req.getRequestURI().replaceAll(req.getContextPath(), "");
			for (MenuBean menu : menus) {
				if (uri.equalsIgnoreCase(menu.getUri())) {
					isMatched = true;
					break;
				}
			}
			if (!isMatched) {
				req.setAttribute("message", "您没有访问权限");
				req.getRequestDispatcher("/front/message.jsp").forward(request, response);
				return;
			}
				
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig config) throws ServletException {

	}

}

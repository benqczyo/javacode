package filter;

import java.io.IOException;
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

import dao.RoleDao;
import dao.impl.RoleDaoImpl;
import domain.AccountBean;
import domain.MenuBean;
import domain.RoleBean;

public class PermissionFilter implements Filter {
	
	private RoleDao rDao = new RoleDaoImpl();

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session =  req.getSession();
		AccountBean account = (AccountBean) session.getAttribute("account");
		if (account != null) {
			Set<MenuBean> menus = new HashSet<MenuBean>();
			List<RoleBean> roles = rDao.findAllRoles();
			if (roles != null) {
				for (RoleBean role : roles) {
					for (MenuBean menu : role.getMenus()) {
						menus.add(menu);
					}
				}
				if (menus.contains(req.getRequestURI().replaceAll(req.getContextPath(), ""))) chain.doFilter(req, res);
			}
		} else {
			res.sendRedirect(req.getContextPath() + "/router?action=show&view=login");
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

}

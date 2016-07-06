package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import service.BussinessService;
import service.impl.BussinessServiceImpl;
import utils.FormBeanUtils;

import domain.AccountBean;
import domain.MenuBean;
import domain.Page;
import domain.RoleBean;
import exception.DaoException;
import formbean.impl.AddAccountFormBean;
import formbean.impl.AddMenuFormBean;
import formbean.impl.AddRoleFormBean;
import formbean.impl.ChangeAccountFormBean;
import formbean.impl.ChangeMenuFormBean;
import formbean.impl.ChangeRoleFormBean;
import formbean.impl.LoginFormBean;

public class Router extends HttpServlet {
	
	private BussinessService service = new BussinessServiceImpl();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("listAllMenus".equalsIgnoreCase(action)) {
			listAllMenus(request, response);
			return;
		}
		if ("addMenu".equalsIgnoreCase(action)) {
			addMenu(request, response);
			return;
		}
		if ("delMenus".equalsIgnoreCase(action)) {
			delMenus(request, response);
			return;
		}
		if ("delMenu".equalsIgnoreCase(action)) {
			delMenu(request, response);
			return;
		}
		if ("changeMenu".equalsIgnoreCase(action)) {
			changeMenu(request, response);
			return;
		}
		if ("updateMenu".equalsIgnoreCase(action)) {
			updateMenu(request, response);
			return;
		}
		if ("listAllRoles".equalsIgnoreCase(action)) {
			listAllRoles(request, response);
			return;
		}
		if ("addRole".equalsIgnoreCase(action)) {
			addRole(request, response);
			return;
		}
		if ("delRoles".equalsIgnoreCase(action)) {
			delRoles(request, response);
			return;
		}
		if ("changeRole".equalsIgnoreCase(action)) {
			changeRole(request, response);
			return;
		}
		if ("updateRole".equalsIgnoreCase(action)) {
			updateRole(request, response);
			return;
		}
		if ("delRole".equalsIgnoreCase(action)) {
			delRole(request, response);
			return;
		}
		if ("assignMenu".equalsIgnoreCase(action)) {
			assignMenu(request, response);
			return;
		}
		if ("doAssignMenu".equalsIgnoreCase(action)) {
			doAssignMenu(request, response);
			return;
		}
		if ("listAllAccounts".equalsIgnoreCase(action)) {
			listAllAccounts(request, response);
			return;
		}
		if ("addAccount".equalsIgnoreCase(action)) {
			addAccount(request, response);
			return;
		}
		if ("changeAccount".equalsIgnoreCase(action)) {
			changeAccount(request, response);
			return;
		}
		if ("updateAccount".equalsIgnoreCase(action)) {
			updateAccount(request, response);
			return;
		}
		if ("assignRole".equalsIgnoreCase(action)) {
			assignRole(request, response);
			return;
		}
		if ("doAssignRole".equalsIgnoreCase(action)) {
			doAssignRole(request, response);
			return;
		}
		if ("defaultPage".equalsIgnoreCase(action)) {
			defaultPage(request, response);
			return;
		}
		if ("login".equalsIgnoreCase(action)) {
			login(request, response);
			return;
		}
		if ("logout".equalsIgnoreCase(action)) {
			logout(request, response);
			return;
		}
	}

	private void updateAccount(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ChangeAccountFormBean formBean = null;
		try {
			formBean = FormBeanUtils.fill(request, ChangeAccountFormBean.class);
			if (formBean.validate() == true) {
				AccountBean account = service.findAccountById(formBean.getId());
				/*if (service.checkAccount(account.getPassword(), formBean.getOldPassword())) {
					account.setPassword(formBean.getNewPassword());
					service.updateAccount(account);
					
				}*/
			} else {
				request.setAttribute("formBean", formBean);
				request.getRequestDispatcher("/manager/changeAccount.jsp").forward(request, response);
			}
		} catch (DaoException e) {
			e.printStackTrace();
			formBean.getMessages().put("result", "修改账号失败");
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/manager/changeAccount.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

	private void changeAccount(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id != null && !id.trim().equals("")) {
			AccountBean account = service.findAccountById(id);
			if (account != null) {
				ChangeAccountFormBean formBean = new ChangeAccountFormBean();
				formBean.setId(id);
				formBean.setName(account.getName());
				request.setAttribute("formBean", formBean);
				request.getRequestDispatcher("/manager/changeAccount.jsp").forward(request, response);
			}
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath());
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		LoginFormBean formBean = null;
		try {
			formBean = FormBeanUtils.fill(request, LoginFormBean.class);
			if (formBean.validate()) {
				AccountBean account = new AccountBean();
				BeanUtils.copyProperties(account, formBean);
				if ((account = service.login(account.getName(), account.getPassword())) != null) {
					request.getSession().setAttribute("account", account);
					response.sendRedirect(request.getContextPath());
				} else {
					formBean.getMessages().put("result", "账号或者密码错误");
					request.setAttribute("formBean", formBean);
					request.getRequestDispatcher("/front/login.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("formBean", formBean);
				request.getRequestDispatcher("/front/login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

	private void defaultPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("menus", service.findAllMenus());
		request.getRequestDispatcher("/front/").forward(request, response);
	}

	private void doAssignRole(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String accountId = request.getParameter("accountId");
		if (accountId != null && !accountId.trim().equals("")) {
			String[] roleIds = request.getParameterValues("roleId");
			if (roleIds != null && roleIds.length > 0) {
				service.assignRole(accountId, roleIds);
			} else {
				service.delAssignedRoles(accountId);
			}
		}
		response.sendRedirect(request.getContextPath() + "/router?action=listAllAccounts");
	}

	private void assignRole(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id != null && !id.trim().equals("")) {
			request.setAttribute("account", service.findAccountById(id));
			request.setAttribute("roles", service.findAllRoles());
			request.getRequestDispatcher("/manager/assignRole.jsp").forward(request, response);
		}
	}

	private void addAccount(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AddAccountFormBean formBean = null;
		try {
			formBean = FormBeanUtils.fill(request, AddAccountFormBean.class);
			if (formBean.validate() == true) {
				AccountBean account = new AccountBean();
				BeanUtils.copyProperties(account, formBean);
				service.addAccount(account);
				response.sendRedirect(request.getContextPath() + "/router?action=listAllAccounts");
			} else {
				request.setAttribute("formBean", formBean);
				request.getRequestDispatcher("/manager/addAccount.jsp").forward(request, response);
			}
		} catch (DaoException e) {
			e.printStackTrace();
			formBean.getMessages().put("result", "添加角色失败，可能是角色名重复");
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/manager/addRole.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

	private void listAllAccounts(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("page", getPage(new AccountBean(), request));
		request.getRequestDispatcher("/manager/listAllAccount.jsp").forward(request, response);
	}

	private void doAssignMenu(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String roleId = request.getParameter("roleId");
		if (roleId != null && !roleId.trim().equals("")) {
			String[] menuIds = request.getParameterValues("menuId");
			if (menuIds != null && menuIds.length > 0) {
				service.assignMenu(roleId, menuIds);
			} else {
				service.delAssignedMenus(roleId);
			}
		}
		response.sendRedirect(request.getContextPath() + "/router?action=listAllRoles");
	}

	private void assignMenu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id != null && !id.trim().equals("")) {
			request.setAttribute("role", service.findRoleById(id));
			request.setAttribute("menus", service.findAllMenus());
			request.getRequestDispatcher("/manager/assignMenu.jsp").forward(request, response);
		}
	}

	private void delRole(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		if (id != null && !id.trim().isEmpty()) {
			service.delRelationsByRoleId(id);
			service.delRoleById(id);
		}
		response.sendRedirect(request.getContextPath() + "/router?action=listAllRoles");
		
	}

	private void updateRole(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ChangeRoleFormBean formBean = null;
		try {
			formBean = FormBeanUtils.fill(request, ChangeRoleFormBean.class);
			if (formBean.validate() == true) {
				RoleBean role = new RoleBean();
				BeanUtils.copyProperties(role, formBean);
				service.updateRole(role);
				response.sendRedirect(request.getContextPath() + "/router?action=listAllRoles");
			} else {
				request.setAttribute("formBean", formBean);
				request.getRequestDispatcher("/manager/changeRole.jsp").forward(request, response);
			}
		} catch (DaoException e) {
			e.printStackTrace();
			formBean.getMessages().put("result", "修改角色失败，可能是角色名重复");
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/manager/changeRole.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

	private void changeRole(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id != null && !id.trim().isEmpty()) {
			RoleBean role = service.findRoleById(id);
			if (role == null) {
				response.sendRedirect(request.getContextPath() + "/router?action=showAllRoles");
				return;
			}
			ChangeRoleFormBean formBean = new ChangeRoleFormBean();
			try {
				BeanUtils.copyProperties(formBean, role);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/manager/changeRole.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/router?action=showAllRoles");
		}
	}

	private void delRoles(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String[] ids = request.getParameterValues("ids");
		if (ids != null && ids.length > 0) {
			for (String id : ids) {
				if (!id.trim().isEmpty()) {
					service.delRelationsByRoleId(id);
					service.delRoleById(id);
				}
			}
		}
		response.sendRedirect(request.getContextPath() + "/router?action=listAllRoles");
	}

	private void addRole(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AddRoleFormBean formBean = null;
		try {
			formBean = FormBeanUtils.fill(request, AddRoleFormBean.class);
			if (formBean.validate() == true) {
				RoleBean role = new RoleBean();
				BeanUtils.copyProperties(role, formBean);
				service.addRole(role);
				response.sendRedirect(request.getContextPath() + "/router?action=listAllRoles");
			} else {
				request.setAttribute("formBean", formBean);
				request.getRequestDispatcher("/manager/addRole.jsp").forward(request, response);
			}
		} catch (DaoException e) {
			e.printStackTrace();
			formBean.getMessages().put("result", "添加角色失败，可能是角色名重复");
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/manager/addRole.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

	private void listAllRoles(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("page", getPage(new RoleBean(), request));
		request.getRequestDispatcher("/manager/listAllRoles.jsp").forward(request, response);
	}

	private void changeMenu(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String id = request.getParameter("id");
		if (id != null && !id.trim().isEmpty()) {
			MenuBean menu = service.findMenuById(id);
			if (menu == null) {
				response.sendRedirect(request.getContextPath() + "/router?action=showAllMenus");
				return;
			}
			ChangeMenuFormBean formBean = new ChangeMenuFormBean();
			try {
				BeanUtils.copyProperties(formBean, menu);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/manager/changeMenu.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/router?action=showAllMenus");
		}
	}

	private void listAllMenus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("page", getPage(new MenuBean(), request));
		request.getRequestDispatcher("/manager/listAllMenus.jsp").forward(request, response);
	}
	
	private void addMenu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AddMenuFormBean formBean = null;
		try {
			formBean = FormBeanUtils.fill(request, AddMenuFormBean.class);
			if (formBean.validate() == true) {
				MenuBean menu = new MenuBean();
				BeanUtils.copyProperties(menu, formBean);
				service.addMenu(menu);
				response.sendRedirect(request.getContextPath() + "/router?action=listAllMenus");
			} else {
				request.setAttribute("formBean", formBean);
				request.getRequestDispatcher("/manager/addMenu.jsp").forward(request, response);
			}
		} catch (DaoException e) {
			e.printStackTrace();
			formBean.getMessages().put("result", "添加菜单失败，可能是菜单标题重复");
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/manager/addMenu.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

	private void delMenu(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		if (id != null && !id.trim().isEmpty()) {
			service.delRelationsByMenuId(id);
			service.delMenuById(id);
		}
		response.sendRedirect(request.getContextPath() + "/router?action=listAllMenus");
	}

	
	private void delMenus(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String[] ids = request.getParameterValues("ids");
		if (ids != null && ids.length > 0) {
			for (String id : ids) {
				if (!id.trim().isEmpty()) {
					service.delRelationsByMenuId(id);
					service.delMenuById(id);
				}
			}
		}
		response.sendRedirect(request.getContextPath() + "/router?action=listAllMenus");
	}
	
	private void updateMenu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ChangeMenuFormBean formBean = null;
		try {
			formBean = FormBeanUtils.fill(request, ChangeMenuFormBean.class);
			if (formBean.validate() == true) {
				MenuBean menu = new MenuBean();
				BeanUtils.copyProperties(menu, formBean);
				service.updateMenu(menu);
				response.sendRedirect(request.getContextPath() + "/router?action=listAllMenus");
			} else {
				request.setAttribute("formBean", formBean);
				request.getRequestDispatcher("/manager/changeMenu.jsp").forward(request, response);
			}
		} catch (DaoException e) {
			e.printStackTrace();
			formBean.getMessages().put("result", "修改菜单失败，可能是菜单标题重复");
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/manager/changeMenu.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
	
	private Page getPage(Object target, HttpServletRequest request) {
		String pageId = request.getParameter("pageId");
		if (pageId == null || pageId.trim().equals("") || !pageId.matches("^[1-9][0-9]*$")) pageId = "1";
		ServletConfig config = getServletConfig();
		String pageRange = config.getInitParameter("pageRange");
		String pageRecords = config.getInitParameter("pageRecords");
		if (pageRange == null || pageRange.trim().equals("") || !pageRange.matches("^[1-9][0-9]*$")) pageRange = "4";
		if (pageRecords == null || pageRecords.trim().equals("") || !pageRecords.matches("^[1-9][0-9]*$")) pageRecords = "10";
		return service.getPage(target, Integer.parseInt(pageRange), Integer.parseInt(pageRecords), pageId);
	}
	
}

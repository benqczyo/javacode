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

import domain.MenuBean;
import domain.Page;
import domain.RoleBean;
import exception.DaoException;
import formbean.impl.AddMenuFormBean;
import formbean.impl.AddRoleFormBean;
import formbean.impl.ChangeMenuFormBean;
import formbean.impl.ChangeRoleFormBean;

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
			formBean.getMessages().put("result", "�޸Ľ�ɫʧ�ܣ������ǽ�ɫ���ظ�");
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
			formBean.getMessages().put("result", "��ӽ�ɫʧ�ܣ������ǽ�ɫ���ظ�");
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
			formBean.getMessages().put("result", "��Ӳ˵�ʧ�ܣ������ǲ˵������ظ�");
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
			formBean.getMessages().put("result", "�޸Ĳ˵�ʧ�ܣ������ǲ˵������ظ�");
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

package controller;

import java.awt.MenuBar;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BEncoderStream;

import domain.MenuBean;
import domain.RoleBean;
import exception.DaoException;

import formbean.impl.AddMenuFormBean;
import formbean.impl.AddRoleFormBean;
import formbean.impl.UpdateRoleFormBean;
import formbean.impl.UpdateMenuFormBean;

import service.BussinessService;
import service.impl.BussinessServiceImpl;
import utils.FormBeanUtils;

public class Router extends HttpServlet {

	private final String viewPath = "/WEB-INF/pages/views/";
	
	private BussinessService service = new BussinessServiceImpl();

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");

		// ��ʾJspҳ��
		if ("show".equalsIgnoreCase(action)) {
			showAction(request, response);
			return;
		}
		
		if ("addMenu".equalsIgnoreCase(action)) {
			addMenuAction(request, response);
			return;
		}
		
		if ("addRole".equalsIgnoreCase(action)) {
			addRoleAction(request, response);
			return;
		}
		
		if ("delMenu".equalsIgnoreCase(action)) {
			delMenuAction(request, response);
			return;
		}
		
		if ("delMenus".equalsIgnoreCase(action)) {
			delMenusAction(request, response);
			return;
		}
		
		if ("delRole".equalsIgnoreCase(action)) {
			delRoleAction(request, response);
			return;
		}
		
		if ("delRoles".equalsIgnoreCase(action)) {
			delRolesAction(request, response);
			return;
		}
		
		if ("updateMenu".equalsIgnoreCase(action)) {
			updateMenuAction(request, response);
			return;
		}
		
		if ("updateRole".equalsIgnoreCase(action)) {
			updateRoleAction(request, response);
			return;
		}
		
		if ("assignMenu".equalsIgnoreCase(action)) {
			assignMenuAction(request, response);
			return;
		}

	}

	private void assignMenuAction(HttpServletRequest request,
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
		response.sendRedirect(request.getContextPath() + "/router?action=show&view=mgrRole");
	}

	private void updateRoleAction(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		UpdateRoleFormBean formBean = null;
		try {
			// ��ȡFormBean
			formBean = FormBeanUtils.fill(request, UpdateRoleFormBean.class);
			// ����FormBean�Ƿ���Ч
			// ��Ч
			if (formBean.validate()) {
				// ��ӵ����ݿ�
				RoleBean role = new RoleBean();
				BeanUtils.copyProperties(role, formBean);
				service.updateRole(role);
				response.sendRedirect(request.getContextPath() + "/router?action=show&view=mgrRole");
			// ��Чת�������ҳ����ʾ������Ϣ
			} else {
				request.setAttribute("formBean", formBean);
				request.getRequestDispatcher("/WEB-INF/pages/views/mgrUpdateRole.jsp").forward(request, response);
			}
		} catch (DaoException e) {
			// �����Ӳ��ɹ�ת�������ҳ����ʾ������Ϣ
			e.printStackTrace();
			formBean.getMessages().put("result", "��ɫ�ظ�");
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/WEB-INF/pages/views/mgrUpdateRole.jsp").forward(request, response);
		} catch (Exception e) {
			// ������ת��Ϣҳ��
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/router?action=show&view=messages");
		}	
	}

	private void delRolesAction(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			String[] ids = request.getParameterValues("ids");
			if (ids != null && ids.length > 0) {
				service.delRolesByIds(ids);
				response.sendRedirect(request.getContextPath() + "/router?action=show&view=mgrRole");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/router?action=show&view=messages");
		}	
	}

	private void delRoleAction(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			String id = request.getParameter("id");
			if (id != null && !id.trim().equalsIgnoreCase("")) {
				service.delRoleById(id);
				response.sendRedirect(request.getContextPath() + "/router?action=show&view=mgrRole");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/router?action=show&view=messages");
		}
		
	}

	private void addRoleAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AddRoleFormBean formBean = null;
		try {
			// ��ȡFormBean
			formBean = FormBeanUtils.fill(request, AddRoleFormBean.class);
			// ����FormBean�Ƿ���Ч
			// ��Ч
			if (formBean.validate()) {
				// ��ӵ����ݿ�
				RoleBean role = new RoleBean();
				BeanUtils.copyProperties(role, formBean);
				service.addRole(role);
				response.sendRedirect(request.getContextPath() + "/router?action=show&view=mgrRole");
			// ��Чת�������ҳ����ʾ������Ϣ
			} else {
				request.setAttribute("formBean", formBean);
				request.getRequestDispatcher("/WEB-INF/pages/views/mgrAddRole.jsp").forward(request, response);
			}
		} catch (DaoException e) {
			// �����Ӳ��ɹ�ת�������ҳ����ʾ������Ϣ
			e.printStackTrace();
			formBean.getMessages().put("result", "��ɫ�ظ�");
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/WEB-INF/pages/views/mgrAddRole.jsp").forward(request, response);
		} catch (Exception e) {
			// ������ת��Ϣҳ��
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/router?action=show&view=messages");
		}
		
	}

	private void delMenuAction(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		try {
			String id = request.getParameter("id");
			if (id != null && !id.trim().equalsIgnoreCase("")) {
				service.delMenuById(id);
				response.sendRedirect(request.getContextPath() + "/router?action=show&view=mgrMenu");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/router?action=show&view=messages");
		}
		
	}

	private void updateMenuAction(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		UpdateMenuFormBean formBean = null;
		try {
			// ��ȡFormBean
			formBean = FormBeanUtils.fill(request, UpdateMenuFormBean.class);
			// ����FormBean�Ƿ���Ч
			// ��Ч
			if (formBean.validate()) {
				// ��ӵ����ݿ�
				MenuBean menu = new MenuBean();
				BeanUtils.copyProperties(menu, formBean);
				service.updateMenu(menu);
				response.sendRedirect(request.getContextPath() + "/router?action=show&view=mgrMenu");
			// ��Чת�������ҳ����ʾ������Ϣ
			} else {
				request.setAttribute("formBean", formBean);
				request.getRequestDispatcher("/WEB-INF/pages/views/mgrUpdateMenu.jsp").forward(request, response);
			}
		} catch (DaoException e) {
			// �����Ӳ��ɹ�ת�������ҳ����ʾ������Ϣ
			e.printStackTrace();
			formBean.getMessages().put("result", "��ɫ�ظ�");
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/WEB-INF/pages/views/mgrUpdateRole.jsp").forward(request, response);
		} catch (Exception e) {
			// ������ת��Ϣҳ��
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/router?action=show&view=messages");
		}	
	}

	private void delMenusAction(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			String[] ids = request.getParameterValues("ids");
			if (ids != null && ids.length > 0) {
				service.delMenusByIds(ids);
				response.sendRedirect(request.getContextPath() + "/router?action=show&view=mgrMenu");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/router?action=show&view=messages");
		}
	}

	private void addMenuAction(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		AddMenuFormBean formBean = null;
		try {
			// ��ȡFormBean
			formBean = FormBeanUtils.fill(request, AddMenuFormBean.class);
			// ����FormBean�Ƿ���Ч
			// ��Ч
			if (formBean.validate()) {
				// ��ӵ����ݿ�
				MenuBean menu = new MenuBean();
				BeanUtils.copyProperties(menu, formBean);
				service.addMenu(menu);
				response.sendRedirect(request.getContextPath() + "/router?action=show&view=mgrMenu");
			// ��Чת�������ҳ����ʾ������Ϣ
			} else {
				request.setAttribute("formBean", formBean);
				request.getRequestDispatcher("/WEB-INF/pages/views/mgrAddMenu.jsp").forward(request, response);
			}
		} catch (DaoException e) {
			// �����Ӳ��ɹ�ת�������ҳ����ʾ������Ϣ
			e.printStackTrace();
			formBean.getMessages().put("result", "�˵����ظ�");
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/WEB-INF/pages/views/mgrAddMenu.jsp").forward(request, response);
		} catch (Exception e) {
			// ������ת��Ϣҳ��
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/router?action=show&view=messages");
		}
		
	}

	private void showAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String path = null;
		String view = request.getParameter("view");
		
		if ("mgrIndex".equalsIgnoreCase(view)) path = "/manager/index.jsp";
		if ("mgrMenu".equalsIgnoreCase(view)) path = getPage(new MenuBean(), request, "mgrMenu.jsp");
		if ("mgrAddMenu".equalsIgnoreCase(view)) path = viewPath + "mgrAddMenu.jsp";
		if ("mgrUpdateMenu".equalsIgnoreCase(view)) {
			String id = request.getParameter("id");
			if (id != null && !id.trim().equalsIgnoreCase("")) {
				MenuBean menu = service.findMenuById(id);
				if (menu == null) {
					response.sendRedirect(request.getContextPath() + "/router?action=show&view=messages");
					return;
				}
				UpdateMenuFormBean formBean = new UpdateMenuFormBean();
				try {
					BeanUtils.copyProperties(formBean, menu);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
				request.setAttribute("formBean", formBean);
				path = viewPath + "mgrUpdateMenu.jsp";
			}
		}
		if ("mgrUpdateRole".equalsIgnoreCase(view)) {
			String id = request.getParameter("id");
			if (id != null && !id.trim().equalsIgnoreCase("")) {
				RoleBean role = service.findRoleById(id);
				if (role == null) {
					response.sendRedirect(request.getContextPath() + "/router?action=show&view=messages");
					return;
				}
				UpdateRoleFormBean formBean = new UpdateRoleFormBean();
				try {
					BeanUtils.copyProperties(formBean, role);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
				request.setAttribute("formBean", formBean);
				path = viewPath + "mgrUpdateRole.jsp";
			}
		}
		if ("mgrRole".equalsIgnoreCase(view)) path = getPage(new RoleBean(), request, "mgrRole.jsp");
		if ("mgrAddRole".equalsIgnoreCase(view)) path = viewPath + "mgrAddRole.jsp";
		if ("mgrAssignMenu".equalsIgnoreCase(view)) {
			String id = request.getParameter("id");
			if (id != null && !id.trim().equals("")) {
				request.setAttribute("role", service.findRoleById(id));
				request.setAttribute("menus", service.findAllMenus());
				path = viewPath + "mgrAssignMenu.jsp";
			}
		}
		if ("mgrAccount".equalsIgnoreCase(view)) path = viewPath + "mgrAccount.jsp";
		
		request.getRequestDispatcher(path).forward(request, response);
	}

	private String getPage(Object target, HttpServletRequest request, String view) {
		String pageId = request.getParameter("pageId");
		if (pageId == null || pageId.trim().equals("") || !pageId.matches("[1-9]+")) pageId = "1";
		ServletConfig config = getServletConfig();
		String pageRange = config.getInitParameter("pageRange");
		String pageRecords = config.getInitParameter("pageRecords");
		if (pageRange == null || pageRange.trim().equals("") || !pageRange.matches("[1-9]+")) pageRange = "4";
		if (pageRecords == null || pageRecords.trim().equals("") || !pageRecords.matches("[1-9]+")) pageRecords = "10";
		request.setAttribute("page", service.getPage(target, Integer.parseInt(pageRange), Integer.parseInt(pageRecords), pageId));
		return viewPath + view;
	}

}

package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.validator.util.GetConstructor;

import service.Service;
import service.impl.ServiceImpl;
import utils.FormBeanUtils;
import utils.IdUtils;
import domain.impl.BookBean;
import domain.impl.CategoryBean;
import exception.AddBookException;
import exception.AddCategoryException;
import formbean.impl.AddBookFormBean;
import formbean.impl.AddCategoryFormBean;
import formbean.impl.UpdateCategoryFormBean;

public class Router extends HttpServlet {

	private Service service = new ServiceImpl();

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("listAllCategories".equalsIgnoreCase(action)) {
			listAllCategories(request, response);
			return;
		}
		if ("addCategory".equalsIgnoreCase(action)) {
			addCategory(request, response);
			return;
		}
		if ("delCategory".equalsIgnoreCase(action)) {
			delCategory(request, response);
			return;
		}
		if ("showUpdateCategoryPage".equalsIgnoreCase(action)) {
			showUpdateCategoryPage(request, response);
			return;
		}
		if ("updateCategory".equalsIgnoreCase(action)) {
			updateCategory(request, response);
			return;
		}
		if ("listAllBooks".equalsIgnoreCase(action)) {
			listAllBooks(request, response);
			return;
		}
		if ("showAddBookPage".equalsIgnoreCase(action)) {
			showAddBookPage(request, response);
			return;
		}
		if ("doAddBook".equalsIgnoreCase(action)) {
			doAddBook(request, response);
			return;
		}
	}

	private void doAddBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		FileItemFactory defaultFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(defaultFileItemFactory);
		if (!sfu.isMultipartContent(request))
			throw new ServletException("请求内容错误");
		String uploadPath = getServletContext().getRealPath("/WEB-INF/upload");
		AddBookFormBean formBean = new AddBookFormBean();
		try {
			List<FileItem> items = sfu.parseRequest(request);
			for (FileItem item : items) {
				if (item.isFormField()) {
					BeanUtils.setProperty(formBean, item.getFieldName(),
							item.getString(request.getCharacterEncoding()));
				} else {
					String fileName = item.getName();
					if (!fileName.trim().isEmpty() && item.getContentType().startsWith("image")) {
						String fileNamePrefix = IdUtils.generateId();
						fileName = String.format("%s_%s", fileNamePrefix,
								fileName.substring(fileName.lastIndexOf("\\") + 1));
						formBean.setPic(fileName);
						InputStream in = item.getInputStream();
						OutputStream out = new FileOutputStream(new File(
								String.format("%s/%s", uploadPath, fileName)));
						int len = -1;
						byte[] data = new byte[1024];
						while ((len = in.read(data)) != -1)
							out.write(data, 0, len);
						out.close();
						in.close();
						item.delete();
					}
				}
			}
			formBean.setCategories(service.findAllCategory());
			if (!formBean.isValidated()) {
				request.setAttribute("formBean", formBean);
				request.getRequestDispatcher("/manager/addBook.jsp").forward(request, response);
				return;
			}
			BookBean book = new BookBean();
			BeanUtils.copyProperties(book, formBean);
			book.setCategory(service.findCategoryById(book.getCategoryId()));
			service.addBook(book);
			response.sendRedirect(request.getContextPath()
					+ "/router?action=listAllBooks");
		} catch (AddBookException e) {
			e.printStackTrace();
			formBean.getMessages().put("result", "添加书籍失败，请检查输入项目");
			request.setAttribute("formBean", formBean);
			new File(String.format("%s/%s", uploadPath, formBean.getPic())).delete();
			request.getRequestDispatcher("/manager/addBook.jsp").forward(
					request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

	private void showAddBookPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AddBookFormBean formBean = new AddBookFormBean();
		formBean.setCategories(service.findAllCategory());
		request.setAttribute("formBean", formBean);
		request.getRequestDispatcher("/manager/addBook.jsp").forward(request,
				response);
	}

	private void listAllBooks(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pageId = request.getParameter("pageId");
		ServletConfig config = getServletConfig();
		String recordsOfSinglePage = config
				.getInitParameter("recordsOfSinglePage");
		String buttonsOfSinglePage = config
				.getInitParameter("buttonsOfSinglePage");
		request.setAttribute("page", service.getPage(new BookBean(),
				recordsOfSinglePage, buttonsOfSinglePage, pageId));
		request.getRequestDispatcher("/manager/listAllBooks.jsp").forward(
				request, response);
	}

	private void showUpdateCategoryPage(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			CategoryBean category = service.findCategoryById(request
					.getParameter("id"));
			UpdateCategoryFormBean formBean = new UpdateCategoryFormBean();
			BeanUtils.copyProperties(formBean, category);
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/manager/updateCategory.jsp")
					.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/messages.jsp");
		}
	}

	private void updateCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UpdateCategoryFormBean formBean = FormBeanUtils.fill(
				UpdateCategoryFormBean.class, request);
		if (formBean == null) {
			response.sendRedirect(request.getContextPath() + "/messages.jsp");
			return;
		}
		if (!formBean.isValidated()) {
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/manager/updateCategory.jsp")
					.forward(request, response);
			return;
		}
		try {
			CategoryBean category = new CategoryBean();
			BeanUtils.copyProperties(category, formBean);
			service.updateCategory(category);
			response.sendRedirect(request.getContextPath()
					+ "/router?action=listAllCategories");
		} catch (AddCategoryException e) {
			e.printStackTrace();
			formBean.getMessages().put("result", "更新分类失败,可能的原因是分类已存在");
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/manager/addCategory.jsp").forward(
					request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/messages.jsp");
		}
	}

	private void delCategory(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			service.delCategoryById(request.getParameter("id"));
			response.sendRedirect(request.getContextPath()
					+ "/router?action=listAllCategories");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/messages.jsp");
		}
	}

	private void addCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AddCategoryFormBean formBean = FormBeanUtils.fill(
				AddCategoryFormBean.class, request);
		if (formBean == null) {
			response.sendRedirect(request.getContextPath() + "/messages.jsp");
			return;
		}
		if (!formBean.isValidated()) {
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/manager/addCategory.jsp").forward(
					request, response);
			return;
		}
		try {
			CategoryBean category = new CategoryBean();
			BeanUtils.copyProperties(category, formBean);
			service.addCategory(category);
			response.sendRedirect(request.getContextPath()
					+ "/manager/addCategory.jsp");
		} catch (AddCategoryException e) {
			e.printStackTrace();
			formBean.getMessages().put("result", "添加分类失败,可能的原因是分类已存在");
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/manager/addCategory.jsp").forward(
					request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/messages.jsp");
		}
	}

	private void listAllCategories(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pageId = request.getParameter("pageId");
		ServletConfig config = getServletConfig();
		String recordsOfSinglePage = config.getInitParameter("recordsOfSinglePage");
		String buttonsOfSinglePage = config.getInitParameter("buttonsOfSinglePage");
		request.setAttribute("page", service.getPage(new CategoryBean(), recordsOfSinglePage, buttonsOfSinglePage, pageId));
		request.getRequestDispatcher("/manager/listAllCategories.jsp").forward(request, response);
	}

}

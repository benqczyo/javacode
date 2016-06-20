package com.benqcz.ikanke.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

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

import com.benqcz.ikanke.domain.Page;
import com.benqcz.ikanke.domain.impl.BookBean;
import com.benqcz.ikanke.domain.impl.CategoryBean;
import com.benqcz.ikanke.exception.AddBookException;
import com.benqcz.ikanke.exception.AddCategoryException;
import com.benqcz.ikanke.proxy.ServiceProxy;
import com.benqcz.ikanke.service.Service;
import com.benqcz.ikanke.utils.FormBeanUtils;
import com.benqcz.ikanke.utils.IdUtils;
import com.benqcz.ikanke.web.formbean.impl.AddBookFormBean;
import com.benqcz.ikanke.web.formbean.impl.AddCategoryFormBean;
import com.benqcz.ikanke.web.formbean.impl.UpdateBookFormBean;
import com.benqcz.ikanke.web.formbean.impl.UpdateCategoryFormBean;

public class Router extends HttpServlet {

	private Service service = ServiceProxy.getInstance();

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
		if ("showCover".equalsIgnoreCase(action)) {
			showCover(request, response);
			return;
		}
		if ("delBook".equalsIgnoreCase(action)) {
			delBook(request, response);
			return;
		}
		if ("showUpdateBookPage".equalsIgnoreCase(action)) {
			showUpdateBookPage(request, response);
			return;
		}
		if ("updateBook".equalsIgnoreCase(action)) {
			updateBook(request, response);
			return;
		}
		if ("listHomePage".equalsIgnoreCase(action)) {
			listHomePage(request, response);
			return;
		}
	}

	private void listHomePage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("categories", service.findAllCategories());
		request.getRequestDispatcher("/client/").forward(request, response);
	}

	private void updateBook(HttpServletRequest request,
			HttpServletResponse response) {
		//如果没有上传文件，保留原文件
		//如果上传了文件，得到文件名，删除原来文件，用原来文件名写入新文件
	}

	private void showUpdateBookPage(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			BookBean book = service.findBookById(request.getParameter("id"));
			UpdateBookFormBean formBean = new UpdateBookFormBean();
			BeanUtils.copyProperties(formBean, book);
			formBean.setCategories(service.findAllCategories());
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/manager/updateBook.jsp")
					.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/messages.jsp");
		}
	}

	private void delBook(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			service.delBookById(request.getParameter("id"));
			//删除上传的封面文件，暂时略
			response.sendRedirect(request.getContextPath()
					+ "/router?action=listAllBooks");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/messages.jsp");
		}
	}

	private void showCover(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		try {
			String uploadPath = getServletContext().getRealPath(
					"/WEB-INF//upload");
			String fileName = request.getParameter("pic");
			int hashCode = fileName.hashCode();
			String dir1 = Integer.toString(hashCode & 0x0f);
			String dir2 = Integer.toString((hashCode & 0xf0) >> 4);
			String path = String.format("%s\\%s\\%s\\%s", uploadPath, dir1,
					dir2, fileName);
			InputStream in = new FileInputStream(path);
			OutputStream out = response.getOutputStream();
			int len = -1;
			byte[] data = new byte[1024];
			while ((len = in.read(data)) != -1)
				out.write(data, 0, len);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
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
					if (!fileName.trim().isEmpty()
							&& item.getContentType().startsWith("image")) {
						String fileNamePrefix = IdUtils.generateId();
						fileName = String
								.format("%s_%s", fileNamePrefix,
										fileName.substring(fileName
												.lastIndexOf("\\") + 1));
						formBean.setPic(fileName);
						int hashCode = fileName.hashCode();
						String dir1 = Integer.toString(hashCode & 0x0f);
						String dir2 = Integer.toString((hashCode & 0xf0) >> 4);
						String path = String.format("%s\\%s\\%s", uploadPath,
								dir1, dir2);
						File file = new File(path);
						if (!file.exists())
							file.mkdirs();
						InputStream in = item.getInputStream();
						OutputStream out = new FileOutputStream(new File(
								String.format("%s\\%s", path, fileName)));
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
			formBean.setCategories(service.findAllCategories());
			if (!formBean.isValidated()) {
				request.setAttribute("formBean", formBean);
				request.getRequestDispatcher("/manager/addBook.jsp").forward(
						request, response);
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
		formBean.setCategories(service.findAllCategories());
		request.setAttribute("formBean", formBean);
		request.getRequestDispatcher("/manager/addBook.jsp").forward(request,
				response);
	}

	private void listAllBooks(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String currentPageId = request.getParameter("pageId");
		ServletConfig config = getServletConfig();
		String recordsOfSinglePage = config
				.getInitParameter("recordsOfSinglePage");
		String buttonsOfSinglePage = config
				.getInitParameter("buttonsOfSinglePage");
		request.setAttribute("page", service.getPage(currentPageId,
				recordsOfSinglePage, buttonsOfSinglePage, new BookBean()));
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
		String currentPageId = request.getParameter("pageId");
		ServletConfig config = getServletConfig();
		String recordsOfSinglePage = config
				.getInitParameter("recordsOfSinglePage");
		String buttonsOfSinglePage = config
				.getInitParameter("buttonsOfSinglePage");
		request.setAttribute("page", service.getPage(currentPageId,
				recordsOfSinglePage, buttonsOfSinglePage, new CategoryBean()));
		request.getRequestDispatcher("/manager/listAllCategories.jsp").forward(
				request, response);
	}

}

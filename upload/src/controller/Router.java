package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import sun.java2d.pipe.BufferedBufImgOps;

public class Router extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("doUpload".equalsIgnoreCase(action))
			doUpload(request, response);
	}

	private void doUpload(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String uploadPath = getServletContext().getRealPath("/WEB-INF/files");
		try {
			if (ServletFileUpload.isMultipartContent(request)) {
				ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
				sfu.setFileSizeMax(1024 * 1024 * 4);
				List<FileItem> items = sfu.parseRequest(request);
				StringBuffer sb = new StringBuffer();
				for (FileItem item : items) {
					String key = null;
					StringBuffer val = null;
					if (item.isFormField()) {
						key = item.getFieldName();
						val = new StringBuffer(item.getString("UTF-8"));
					} else {
						if (item.getContentType().startsWith("image")) {
							key = item.getName();
							key = key.substring(key.lastIndexOf("\\") + 1);
							val = new StringBuffer();
							int len = -1;
							byte[] data = new byte[1024];
							InputStream in = item.getInputStream();
							FileOutputStream out = new FileOutputStream(getStorePath(uploadPath, key));
							while ((len = in.read(data)) != -1) {
								out.write(data, 0, len);
							}
							out.close();
							in.close();
						} else {
							key = "不支持的类型";
							val = new StringBuffer("不支持");
						}
					}
					sb.append(String.format("[key=%s value=%s]\n", key, val.toString()));
				}
				response.getWriter().write(sb.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

	private String getStorePath(String uploadPath, String key) {
		String fileName = UUID.randomUUID().toString() + "_" + key;
		int hashCode = fileName.hashCode();
		String dir1 = Integer.toString(hashCode & 0x0f);
		String dir2 = Integer.toString((hashCode & 0xf0) >> 4);
		String path = String.format("%s\\%s\\%s", uploadPath, dir1, dir2);
		File f = new File(path);
		if (!f.exists()) f.mkdirs();
		return path + "\\" + fileName;
	}
}

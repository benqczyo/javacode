package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

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
			HttpServletResponse response) throws ServletException {
		String uploadPath = getServletContext().getRealPath("/files");
		try {
			if (ServletFileUpload.isMultipartContent(request)) {
				ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
				List<FileItem> items = sfu.parseRequest(request);
				StringBuffer sb = new StringBuffer();
				for (FileItem item : items) {
					String key = null;
					StringBuffer val = null;
					if (item.isFormField()) {
						key = item.getFieldName();
						val = new StringBuffer(item.getString());
					} else {
						key = item.getName();
						key = key.substring(key.lastIndexOf("\\") + 1);
						val = new StringBuffer();
						String line = null;
						FileWriter fw = new FileWriter(new File(uploadPath + "/" + key));
						BufferedReader reader = new BufferedReader(new InputStreamReader(item.getInputStream()));
						while ((line = reader.readLine()) != null) {
							val.append(line);
							fw.write(line);
						}
						fw.close();
						reader.close();
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
}

package com.fiesta.controller.component;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;

public class FileUploadTestController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		File attachesDir = new File(request.getSession().getServletContext().getRealPath("/resource/file_upload"));
		
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		fileItemFactory.setRepository(attachesDir);
		fileItemFactory.setSizeThreshold(1024 * 1024);
		ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
		
		try {
			List<FileItem> items = fileUpload.parseRequest(request);
			for(FileItem item : items) {
				if(item.isFormField()) {
					System.out.println("파라미터 명 : " + item.getFieldName());
					System.out.println("파라미터 값 : " + item.getString("utf-8"));
				}else {
					System.out.println("파라미터 명 : " + item.getFieldName());
					System.out.println("파일 명 : " + item.getName());
					System.out.println("파일 크기 : " + item.getSize());
					if(item.getSize() > 0) {
						String separator = File.separator;
						int index = item.getName().lastIndexOf(separator);
						String fileName = item.getName().substring(index + 1);
						File uploadFile = new File(attachesDir + separator + fileName);
						item.write(uploadFile);
					}
				}
			}
		}catch(Exception e) {
			System.out.println("FileUploadTestController :: " + e);
		}
		return new ModelAndView("fileUploadTest.jsp");
	
	}

}

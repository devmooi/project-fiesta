package com.fiesta.controller.component;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.RegisterDaoImpl;
import com.fiesta.model.vo.Company;
import com.fiesta.util.AES256Util;

public class CompanyRegisterController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//파일 업로드
		File attachesDir = new File(request.getSession().getServletContext().getRealPath("/resource/file_upload"));
		
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		fileItemFactory.setRepository(attachesDir);
		fileItemFactory.setSizeThreshold(1024 * 1024);
		ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
		
		String email = "";
		String name = "";
		String pass = "";
		String tel = "";
		String addr = "";
		String img = "";
		String desc = "";
		int categoryCode = 0;
		
		try {
			List<FileItem> items = fileUpload.parseRequest(request);
			for(FileItem item : items) {
				if(item.isFormField()) {
					System.out.println("파라미터 명 : " + item.getFieldName());
					System.out.println("파라미터 값 : " + item.getString("utf-8"));
					if(item.getFieldName().equals("name")) {
						name = item.getString("utf-8");
					}else if(item.getFieldName().equals("email")) {
						email = item.getString("utf-8");
					}else if(item.getFieldName().equals("pass")) {
						pass = item.getString("utf-8");
					}else if(item.getFieldName().equals("categoryCode")) {
						categoryCode = Integer.parseInt(item.getString("utf-8"));
					}else if(item.getFieldName().equals("tel")) {
						tel = item.getString("utf-8");
					}else if(item.getFieldName().equals("addr")) {
						addr += item.getString("utf-8") + " ";
					}else if(item.getFieldName().equals("desc")) {
						desc = item.getString("utf-8");
					}
				}else {
					System.out.println("파라미터 명 : " + item.getFieldName());
					System.out.println("파일 명 : " + item.getName());
					System.out.println("파일 크기 : " + item.getSize());
					if(item.getSize() > 0) {
						String separator = File.separator;
						int index = item.getName().lastIndexOf(separator);
						String fileName = item.getName().substring(index + 1);
						File uploadFile = new File(attachesDir + separator + "company_" + fileName);
						item.write(uploadFile);
						img = "resource/file_upload/company_" + item.getName();
					}
				}
			}
		}catch(Exception e) {
			System.out.println("FileUploadTestController :: " + e);
		}
		
		//비밀번호 암호화
		String key = "aes256-password-key"; 
		AES256Util aes256 = new AES256Util(key);
		
		pass = aes256.encrypt(pass);
		
		
		System.out.println("Encode PWD : " + pass);
				
		
		//DB 등록
		Company company = new Company(email, pass, name, tel, addr, img, desc, categoryCode);
		try {
			RegisterDaoImpl.getInstance().registerCompany(company);
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return new ModelAndView("registerSuccess.jsp", true);
	
	}
}


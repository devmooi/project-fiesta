package com.fiesta.controller.component;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.tribes.util.Arrays;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.ReviewDaoImpl;
import com.fiesta.model.vo.Company;
import com.fiesta.model.vo.Customer;
import com.fiesta.model.vo.Review;
import com.fiesta.model.vo.Service;

public class InsertReviewController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		File attachesDir = new File(request.getSession().getServletContext().getRealPath("/resource/file_upload"));
		ReviewDaoImpl dao = ReviewDaoImpl.getInstance();
		HttpSession session = request.getSession();
		
		Customer cust = new Customer("encore@gmail.com", "java", "1234");
		session.setAttribute("customer", cust);
		
		
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		fileItemFactory.setRepository(attachesDir);
		fileItemFactory.setSizeThreshold(1024 * 1024);
		ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
		
		String type = "";
		int comCode=0;
		String[] arr = {};
		int serviceCode= 0;
		String reviewCode="";
		int reviewScore = 0;
		String reviewDesc="";
		String reviewImg = "./resource/file_upload/";
		try {
			List<FileItem> items = fileUpload.parseRequest(request);
			for(FileItem item : items) {
				if(item.isFormField()) {
					System.out.println("파라미터 명 : " + item.getFieldName());
					System.out.println("파라미터 값 : " + item.getString("utf-8"));
					if(item.getFieldName().equals("reviewScore")) {
						reviewScore =  Integer.parseInt(item.getString("utf-8"));
					}else if(item.getFieldName().equals("comCode")){
						comCode =  Integer.parseInt(item.getString("utf-8"));
					}else if(item.getFieldName().equals("serviceName")){
						arr =  item.getString("utf-8").split(",");
						serviceCode = Integer.parseInt(arr[0]);
					}else if(item.getFieldName().equals("reviewDesc")){
						reviewDesc =  item.getString("utf-8");
					}else if(item.getFieldName().equals("codes")) {
						arr =  item.getString("utf-8").split(",");
						reviewCode = arr[0];
						comCode = Integer.parseInt(arr[1]);
						serviceCode = Integer.parseInt(arr[2]);
					}else if(item.getFieldName().equals("type")) {
						type = item.getString("utf-8");
					}
				}else {
					System.out.println("파라미터 명 : " + item.getFieldName());
					System.out.println("파일 명 : " + item.getName());
					System.out.println("파일 크기 : " + item.getSize());
					if(item.getSize() > 0) {
						String separator = File.separator;
						int index = item.getName().lastIndexOf(separator);
						String fileName = item.getName().substring(index + 1)+comCode+serviceCode;
						File uploadFile = new File(attachesDir + separator + fileName);
						item.write(uploadFile);
						reviewImg+=item.getName();
					}
				}
			}
		}catch(Exception e) {
			System.out.println("FileUploadTestController :: " + e);
		}
		System.out.println("reviewCode : "+reviewCode);
		if(type.equals("1")) {
			System.out.println(dao.isReview(comCode, serviceCode)==true);
			if(dao.isReview(comCode, serviceCode)==true) {
				String[] arr2 = dao.showReview(comCode).getReviewCode().split("-");
				arr2[2]=String.valueOf(Integer.parseInt(arr2[2])+1);
				reviewCode=arr2[0]+"-"+arr2[1]+"-"+arr2[2];
			}else {
				reviewCode=comCode+"-"+serviceCode+"-"+"1";
			}
		}else {
			if(dao.isAnswer(reviewCode+"-%")==true) {
				String[] arr2 = dao.showReview(reviewCode+"-%").getReviewCode().split("-");
				System.out.println(Arrays.toString(arr2));
				arr2[3]=String.valueOf(Integer.parseInt(arr2[3])+1);
				reviewCode=arr2[0]+"-"+arr2[1]+"-"+arr2[2]+"-"+arr2[3];
			}else {
				reviewCode+="-1";
			}
		}
		
		System.out.println("reviewcode"+reviewCode);
		Review review = new Review(reviewCode, reviewScore, reviewImg, reviewDesc,
				(Customer)session.getAttribute("customer"), new Service(serviceCode), new Company(comCode));
		//
		dao.insertReview(review);
		return new ModelAndView("ServiceAllShow.do?companycode="+comCode);
	}

}

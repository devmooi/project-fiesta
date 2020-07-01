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
		// 생성할 객체 미리 생성
		ReviewDaoImpl dao = ReviewDaoImpl.getInstance();
		HttpSession session = request.getSession();
		
		// 현재 페이지는 로그인 상황이나 합치기 전에는 미리 생성해놓음
		Customer cust = new Customer("customer");

		// 파일 업로드 준비
		File attachesDir = new File(request.getSession().getServletContext().getRealPath("/resource/file_upload"));
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		fileItemFactory.setRepository(attachesDir);
		fileItemFactory.setSizeThreshold(1024 * 1024);
		ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
		
		// 들어오는 방식이 getparameter가 아니므로 elseif로 받을 준비
		String type = "";
		int companycode=0;
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
					}else if(item.getFieldName().equals("companycode")){
						companycode =  Integer.parseInt(item.getString("utf-8"));
					}else if(item.getFieldName().equals("serviceName")){
						arr =  item.getString("utf-8").split(",");
						serviceCode = Integer.parseInt(arr[0]);
					}else if(item.getFieldName().equals("reviewDesc")){
						reviewDesc =  item.getString("utf-8");
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
						String fileName = companycode+"_"+serviceCode+item.getName().substring(index + 1);
						File uploadFile = new File(attachesDir + separator + fileName);
						item.write(uploadFile);
						reviewImg = "resource/file_upload/" + fileName;
						System.out.println("이미지 체크 :: " + reviewImg);
					}
				}
			}
		}catch(Exception e) {
			System.out.println("FileUploadTestController :: " + e);
		}
		System.out.println("reviewCode : "+reviewCode);
		
		if(type.equals("1")) {
			System.out.println(dao.isReview(companycode, serviceCode)==true);
			if(dao.isReview(companycode, serviceCode)==true) {
				reviewCode=companycode+"-"+serviceCode+"-"+(dao.lastReviewCode(companycode)+1);
			}else {
				reviewCode=companycode+"-"+serviceCode+"-"+"1";
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
				(Customer)session.getAttribute("customer"), new Service(serviceCode), new Company(companycode));
		System.out.println("review : "+review);
		dao.insertReview(review);
		
		response.sendRedirect("ServiceAllShow.do?companycode="+companycode);
		
		//return new ModelAndView("ServiceAllShow.do?companycode="+comCode);
		return null;
	}

}

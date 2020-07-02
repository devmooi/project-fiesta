package com.fiesta.util;

import java.sql.SQLException;

import com.fiesta.model.dao.CompanyDaoImpl;
import com.fiesta.model.dao.RegisterDaoImpl;
import com.fiesta.model.dao.ReviewDaoImpl;
import com.fiesta.model.vo.Company;
import com.fiesta.model.vo.Customer;
import com.fiesta.model.vo.Review;
import com.fiesta.model.vo.Service;

public class TestCaseInsert {

	public static void main(String[] args) throws SQLException {
		ReviewDaoImpl dao = ReviewDaoImpl.getInstance();
		RegisterDaoImpl rdao =  RegisterDaoImpl.getInstance();
		CompanyDaoImpl cdao = CompanyDaoImpl.getInstance();
		
		//알고리즘 테스트 케이스 추가
		//Customer
		String[] emails = {"wpdud001@gmail.com","encore1@gmail.com","sabu@gmail.com","random@gmail.com","happy@gmail.com",
							"sad@gmail.com","cool@gmail.com","kim@gmail.com","park@gmail.com","Lee@gmail.com"};
		String[] images = {"1_1scenery-5062632_1920.jpg","1_2john-cameron-kPOtEGVdEfQ-unsplash.jpg",
							"1_1nik-shuliahin-C2CYPENZ7LA-unsplash.jpg"};
		
		Customer cust = new Customer();
		String[] custnames= {"james","amy","harry"};
		
		//Service
		Service service = new Service();
		String[] services = {};
		
		//Company
		Company company = new Company();
		
		//Review
		Review review = new Review();
		String reviewCode = "";
		String[] reviewDescs = {"좋아요","보통이에요","별로에요"};
		int reviewScore=0;
		
		/*int comCode =1;
		int cnt=1;
		for(int i=0;i<10;i++) {
			//customer 추가... 이메일이 외부키
			cust.setCustEmail(emails[i]);
			int ranNum2 = (int) Math.floor((Math.random()*3));
			cust.setCustName(custnames[ranNum2]);
			cust.setCustPass("1234");
			cust.setCustTel("02-2220-1234");
			cust.setCustGroup("Encore Academy");
			rdao.registerCustomer(cust);
			//service 추가... 업체코드와 서비스코드가 외부키, 서비스코드는 자동생성
			service.setComCode(comCode);
			cdao.insertService(service);
			comCode++;
			System.out.println("email : "+emails[i]);
			System.out.println(cnt+"회 :: 서비스, 고객");
			cnt++;
		}
		comCode=1;
		int serviceCode= 1;
		int reviewNum=1;
		for(int i=0;i<10;i++) {
			reviewNum=1;
			for(int j=0;j<10;j++) {
				//외부키
				cust.setCustEmail(emails[j]);
				service.setServiceCode(serviceCode);
				company.setComCode(comCode);
				
				//Review 추가... 10개의 업체가 순서대로 서비스를 내고 거기에 
				reviewCode = comCode+"-"+serviceCode+"-"+reviewNum;
				review.setReviewCode(reviewCode);
				reviewScore = (int) Math.floor((Math.random()*6));
				review.setReviewScore(reviewScore);
				int ranNum = (int) Math.floor((Math.random()*3));
				review.setReviewDesc(reviewDescs[ranNum]);
				int ranNum2 = (int) Math.floor((Math.random()*3));
				review.setReviewImg("resource/file_upload/"+images[ranNum2]);
				review.setCustomer(cust);
				review.setService(service);
				review.setCompany(company);
				
				dao.insertReview(review);
				
				//서비스 별로 상승
				reviewNum++;
			}
			comCode++;serviceCode++;
			System.out.println(cnt+"회 ::");
			cnt++;
		}*/
		
		//리뷰 단독으로 등록하기... 암호화 때문에 회원가입 후 입력해주기
		int comCode=1;
		int serviceCode= 1;
		int reviewNum=11;
		String email = "wpdud003@naver.com";//해당하는 이메일 넣기
		for(int j=0;j<10;j++) {
			//외부키
			cust.setCustEmail(email);
			service.setServiceCode(serviceCode);
			company.setComCode(comCode);
			
			//Review 추가... 10개의 업체가 순서대로 서비스를 내고 거기에 
			reviewCode = comCode+"-"+serviceCode+"-"+reviewNum;
			review.setReviewCode(reviewCode);
			reviewScore = (int) Math.floor((Math.random()*6));
			review.setReviewScore(reviewScore);
			int ranNum = (int) Math.floor((Math.random()*3));
			review.setReviewDesc(reviewDescs[ranNum]);
			int ranNum2 = (int) Math.floor((Math.random()*3));
			review.setReviewImg("resource/file_upload/"+images[ranNum2]);
			review.setCustomer(cust);
			review.setService(service);
			review.setCompany(company);
			
			dao.insertReview(review);
			
			comCode++;serviceCode++;
		}
	}
}

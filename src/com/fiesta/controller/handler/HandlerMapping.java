package com.fiesta.controller.handler;

import com.fiesta.controller.Controller;
import com.fiesta.controller.component.*;

public class HandlerMapping {
	private static HandlerMapping handler = new HandlerMapping();
	private HandlerMapping() {}
	public static HandlerMapping getInstance() {
		return handler;
	}

	public Controller createController(String command) {
		Controller controller = null;
		
		// 작업 영역
		if(command.equals("serviceRegister.do")) {
			controller = new ServiceRegisterController();
			System.out.println("ServiceRegisterController 생성됨");
			
		}else if(command.equals("ServiceAllShow.do")) {
			controller = new ServiceAllShowController();
			System.out.println("ServiceAllShowController 생성됨");
			
		}else if(command.equals("ServiceDelete.do")) {
			controller = new ServiceDeleteController();
			System.out.println("ServiceDeleteController 생성됨");
			
		}else if(command.equals("ShowAllCompany.do")) {
			controller = new ShowAllComanyController();
			System.out.println("ShowAllComanyController 생성됨");
			
		}else if(command.equals("ShowAllCompanyByCategory.do")) {
			controller = new ShowAllCompanyByCategoryController();
			System.out.println("ShowAllCompanyListByCategoryController 생성됨");
			
		}else if(command.equals("questionRegister.do")) {
			controller = new QuestionRegisterController();
			System.out.println("QuestionRegisterController 생성됨");
			
		}else if(command.equals("questionAllShow.do")) {
			controller = new QuestionAllShowController();
			System.out.println("QuestionAllShowController 생성됨");
			
		}else if(command.equals("questionView.do")) {
			controller = new QuestionViewController();
			System.out.println("QuestionViewController 생성됨");
			
		}else if(command.equals("questionAllShowByCom.do")) {
			controller = new QuestionAllShowByComController();
			System.out.println("QuestionAllShowByComController 생성됨");
	
		}else if(command.equals("answerRegister.do")) {
			controller = new AnswerRegisterController();
			System.out.println("AnswerRegisterController 생성됨");
			
		}else if(command.equals("answerView.do")) {
			controller = new AnswerViewController();
			System.out.println("AnswerViewController 생성됨");
			
		}else if(command.equals("lookupCompany.do")) {
			controller = new lookupCompanyController();
			System.out.println("lookupCompanyController 생성됨");
			
		}else if(command.equals("SortCompany.do")) {
			controller = new SortCompanyController();
			System.out.println("SortCompanyController 생성됨");

		}else if(command.equals("InsertReview.do")) {
			controller = new InsertReviewController();
			System.out.println("InsertReviewController 생성됨");
			
		}else if(command.equals("fileUploadTest.do")) {
			controller = new FileUploadTestController();

		}else if(command.equals("customerRegister.do")) {
			controller = new CustomerRegisterController();
			System.out.println("CustomerRegisterController 생성됨");
			
		}else if(command.equals("companyRegister.do")) {
			controller = new CompanyRegisterController();
			System.out.println("CompanyRegisterController 생성됨");

		}else if(command.equals("custEmailExist.do")) {
			controller = new CustomerEmailExistController();
			System.out.println("CustomerEmailExistController 생성됨");
			
		}else if(command.equals("comEmailExist.do")) {
			controller = new CompanyEmailExistController();
			System.out.println("CompanyEmailExistController 생성됨");
			
		}else if(command.equals("login.do")) {
			controller = new LoginController();
			System.out.println("LoginController 생성됨");

		}else if(command.equals("ShowService.do")) {
			controller = new ShowServiceController();
			System.out.println("ShowServiceController 생성됨");
		
		}else if(command.equals("wishRegister.do")) {
			controller = new WishRegisterController();
			System.out.println("WishRegisterController 생성됨");
		
		}else if(command.equals("wishList.do")) {
			controller = new WishListController();
			System.out.println("WishListController 생성됨");
		}
		
		
		
		return controller;
	}
}
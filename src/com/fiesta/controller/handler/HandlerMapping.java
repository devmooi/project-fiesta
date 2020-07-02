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
			
		}else if(command.equals("questionDelete.do")) {
			controller = new QuestionDeleteController();
			System.out.println("QuestionAllShowByComController 생성됨");
			
		}else if(command.equals("answerRegister.do")) {
			controller = new AnswerRegisterController();
			System.out.println("AnswerRegisterController 생성됨");
			
		}else if(command.equals("answerDelete.do")) {
			controller = new AnswerDeleteController();
			System.out.println("AnswerDeleteController 생성됨");
			
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

		}else if(command.equals("updateCustomer.do")) {
			controller = new UpdateCustomerController();
			System.out.println("UpdateCustomerController 생성됨");
			
		}else if(command.equals("updateCompany.do")) {
					controller = new UpdateCompanyController();
					System.out.println("UpdateCompanyController 생성됨");
				
		}else if(command.equals("deleteCustomer.do")) {
			controller = new DeleteCustomerController();
			System.out.println("DeleteCustomerController 생성됨");
		
		}else if(command.equals("deleteCompany.do")) {
			controller = new DeleteCompanyController();
			System.out.println("DeleteCompanyController 생성됨");
		
		}else if(command.equals("logout.do")) {
			controller = new LogoutController();
			System.out.println("LogoutController 생성됨");
			
		}else if(command.equals("showAllCustOrder.do")) {
			controller = new ShowAllCustomerOrderController();
			System.out.println("ShowAllCustomerOrderController 생성됨");
			
		}else if(command.equals("showAllCustRequest.do")) {
			controller = new ShowAllCustomerRequestController();
			System.out.println("ShowAllCustomerRequestController 생성됨");

		}else if(command.equals("wishRegister.do")) {
			controller = new WishRegisterController();
			System.out.println("WishRegisterController 생성됨");
		
		}else if(command.equals("wishList.do")) {
			controller = new WishListController();
			System.out.println("WishListController 생성됨");
		
		}else if(command.equals("wishDelete.do")) {
			controller = new WishDeleteController();
			System.out.println("WishDeleteController 생성됨");

		}else if(command.equals("ShowReview.do")) {
			controller = new ShowReviewController();
			System.out.println("ShowReviewController 생성됨");
		
		}else if(command.equals("customerMypage.do")) {
			controller = new CustomerMypageController();
			System.out.println("CustomerMypageController 생성됨");
			
		}else if(command.equals("customerRequest.do")) {
			controller = new CustomerRequestController();
			System.out.println("CustomerRequestController 생성됨");
			
		}else if(command.equals("companyMypage.do")) {
			controller = new CompanyMypageController();
			System.out.println("CompanyMypageController 생성됨");

		}else if(command.equals("passFind.do")) {
			controller = new PassFindController();
			System.out.println("PassFindController 생성됨");
			
		}else if(command.equals("reviewDelete.do")) {
			controller = new DeleteReviewController();
			System.out.println("DeleteReviewController 생성됨");

		}else if(command.equals("ServiceOrder.do")) {
			controller = new ServiceOrderController();
			System.out.println("ServiceOrderController 생성됨");
			
		}else if(command.equals("customerOrderFrom.do")) {
			controller = new CustomerOrderFromController();
			System.out.println("CustomerOrderFromController 생성됨");
		
		}else if(command.equals("orderApprove.do")) {
			controller = new OrderApproveController();
			System.out.println("OrderApproveController 생성됨");
		
		}else if(command.equals("orderReject.do")) {
			controller = new OrderRejectController();
			System.out.println("OrderRejectController 생성됨");
		
		}else if(command.equals("orderCancel.do")) {
			controller = new OrderCancelController();
			System.out.println("OrderCancelController 생성됨");
			
		}else if(command.equals("requestCancel.do")) {
			controller = new RequestCancelController();
			System.out.println("RequestCancelController 생성됨");
		}
		
		
		
		
		return controller;
	}
}
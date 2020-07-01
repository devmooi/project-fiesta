package com.fiesta.controller.component;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.*;
import com.fiesta.model.vo.*;

public class CustomerMypageController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();

		Customer customer = (Customer) session.getAttribute("customer"); // eclispe가 시키는대로 casting만 해주면 됐..!
		String custEmail = customer.getCustEmail();
						
		String path = "customerMypage.jsp";

		//주문내역
		ArrayList<Custorder> custOrderList = CustomerDaoImpl.getInstance().showAllCustOrder(custEmail);
		ArrayList<Custorder> custOrderDetailList = new ArrayList<>();  //얘는 자세히 보는거
		
		//주문한 서비스
		ArrayList<Service> custOrderService = new ArrayList<>();
				
		//고객리스트 자세히 보기
		for(Custorder c : custOrderList) {
			custOrderDetailList.add(CustomerDaoImpl.getInstance().showCustOrder(c.getOrderCode()));
			custOrderService.add(CompanyDaoImpl.getInstance().showService(c.getServiceCode()));
		}
		
		//최종주문승인된 서비스
		ArrayList<Custorderdetail> custOrderFinalDetail = CustomerDaoImpl.getInstance().showAllCustOrderDetail(custEmail);
		
		//주문내역바인딩
		request.setAttribute("custOrderList", custOrderList);
		request.setAttribute("custOrderDetailList", custOrderDetailList);
		//주문한 서비스 바인딩
		request.setAttribute("custOrderService", custOrderService);
		
		//최종주문 승인된 것들 바인딩
		request.setAttribute("custOrderFinalDetail", custOrderFinalDetail);
		
		
		
		//의뢰내역
/*		ArrayList<Custrequest> requestList = new ArrayList<>();
		ArrayList<Custrequestdetail> requestDetailList = new ArrayList<>();
		
		requestList = CustomerDaoImpl.getInstance().showAllCustRequest(custEmail);
		requestDetailList = CustomerDaoImpl.getInstance().showAllCustRequestDetail(custEmail);*/
		
		//문의답변내역
		ArrayList<Question> questionList = QuestionDaoImpl.getInstance().showAllQuestion(custEmail);
		ArrayList<Question> questionDetail = new ArrayList<>();
		ArrayList<Answer> answerList = new ArrayList<>();
		Answer answer = null;
		Question qDetail = null;
		
		for(Question q : questionList) {
			answer = new Answer();
			qDetail = new Question();
			answer = QuestionDaoImpl.getInstance().showAnswer(q.getqCode());
			qDetail = QuestionDaoImpl.getInstance().showQuestion(q.getqCode());
			
			questionDetail.add(qDetail);

			answerList.add(answer);

		}
		
		//나의 찜내역
		ArrayList<Wish> wishlist = WishDaoImpl.getInstance().showAllWish(custEmail);
		
				
		//주문의뢰내역 바인딩

/*		request.setAttribute("requestList", requestList);
		request.setAttribute("requestDetailList", requestDetailList);		*/
		
		//문의답변 바인딩
		request.setAttribute("questionList", questionList);
		request.setAttribute("questionDetail", questionDetail);
		request.setAttribute("answerList", answerList);
		
		//나의 찜내역 바인딩
		request.setAttribute("wishlist", wishlist);

		return new ModelAndView(path);
	}

}

package com.fiesta.controller.component;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.CompanyDaoImpl;
import com.fiesta.model.dao.QuestionDaoImpl;
import com.fiesta.model.dao.ReviewDaoImpl;
import com.fiesta.model.vo.Answer;
import com.fiesta.model.vo.Company;
import com.fiesta.model.vo.Customer;
import com.fiesta.model.vo.Question;
import com.fiesta.model.vo.Review;
import com.fiesta.model.vo.Service;

public class ServiceAllShowController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		int companycode = Integer.parseInt(request.getParameter("companycode"));
		String path = "";
		Answer answer = null;
		Question qDetail = null;
		
		//세션 불러오기
		Customer customer = (Customer) session.getAttribute("customer");
		
		//클릭 시 조회수 증가
		CompanyDaoImpl.getInstance().plusCount(companycode);
		
		//회사 정보 불러오기
		Company companyInfo = CompanyDaoImpl.getInstance().detailViewCompany(companycode);
		
		//서비스 리스트
		ArrayList<Service> serviceList = CompanyDaoImpl.getInstance().showAllService(companycode);
		
		//문의답변
		ArrayList<Question> questionList = QuestionDaoImpl.getInstance().showAllQuestionByCompany(companycode);
		ArrayList<Question> questionDetail = new ArrayList<>();
		ArrayList<Answer> answerList = new ArrayList<>();
		
		for(Question q : questionList) {
			answer = new Answer();
			qDetail = new Question();
			answer = QuestionDaoImpl.getInstance().showAnswer(q.getqCode());
			qDetail = QuestionDaoImpl.getInstance().showQuestion(q.getqCode());
			
			questionDetail.add(qDetail);
			
//			if(answer!=null) {
				answerList.add(answer);
/*			}else { if(answer == null) {
				answer.setaDesc("답변대기중입니다");
				answer.setaDate(" ");
				answerList.add(answer);
			}*/
		}
		
		System.out.println(answerList);
		
		//리뷰 출력
		ArrayList<Review> reviewlist2 = ReviewDaoImpl.getInstance().showAllReviewByCompany(companycode);
		System.out.println("reviewlist : "+reviewlist2);
		
		
		//리뷰수와 평점 출력
		Review review = ReviewDaoImpl.getInstance().showReview(companycode);
		float avg = (float) (Math.round(review.getAvgReviewScore()*100)/100.0);
		review.setAvgReviewScore(avg);
		ArrayList<String> reviewSrcList = new ArrayList<>();
		while(avg>0) {
			reviewSrcList.add("resource/img/star.png");
			avg--;
			if(avg<1&&avg>0) {
				reviewSrcList.add("resource/img/halfstar.png");
				avg=0;
			}
		}
		
		//회사정보 바인딩
		request.setAttribute("companyInfo", companyInfo);
		
		//서비스바인딩
		request.setAttribute("serviceList", serviceList);
		
		//문의답변 바인딩
		request.setAttribute("questionList", questionList);
		request.setAttribute("questionDetail", questionDetail);
		request.setAttribute("answerList", answerList);
		
		//리뷰 바인딩
		request.setAttribute("reviewlist2", reviewlist2);
		request.setAttribute("companycode", companycode);
		request.setAttribute("review", review);
		request.setAttribute("reviewSrcList", reviewSrcList);
				
		path = "company/serviceAllShowResult.jsp";
		
		return new ModelAndView(path);
	}

}

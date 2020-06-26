package com.fiesta.controller.component;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.CompanyDaoImpl;
import com.fiesta.model.dao.QuestionDaoImpl;
import com.fiesta.model.vo.Answer;
import com.fiesta.model.vo.Question;

import com.fiesta.model.dao.ReviewDaoImpl;
import com.fiesta.model.vo.Review;
import com.fiesta.model.vo.Service;

public class ServiceAllShowController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int companycode = Integer.parseInt(request.getParameter("companycode"));
		String path = "";
		Answer answer = null;
		
		//서비스 리스트
		ArrayList<Service> serviceList = CompanyDaoImpl.getInstance().showAllService(companycode);
		
		//문의답변
		ArrayList<Question> questionList = QuestionDaoImpl.getInstance().showAllQuestionByCompany(companycode);
		ArrayList<Answer> answerList = new ArrayList<>();
		
		for(Question q : questionList) {
			answer = new Answer();
			answer = QuestionDaoImpl.getInstance().showAnswer(q.getqCode());
//			if(answer!=null) {
				answerList.add(answer);
/*			}else { if(answer == null) {
				answer.setaDesc("답변대기중입니다");
				answer.setaDate(" ");
				answerList.add(answer);
			}*/
		}
		
		System.out.println(answerList);
		//서비스바인딩
		request.setAttribute("serviceList", serviceList);
		
		//문의답변 바인딩
		request.setAttribute("questionList", questionList);
		request.setAttribute("answerList", answerList);
		
		path = "serviceAllShowResult.jsp";

		ArrayList<Review> list2 = ReviewDaoImpl.getInstance().showAllReview(companycode);
		request.setAttribute("list2", list2);
		request.setAttribute("companycode", companycode);
		path = "./company/serviceAllShowResult.jsp";
		
		return new ModelAndView(path);
	}

}

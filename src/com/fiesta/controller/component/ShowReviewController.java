package com.fiesta.controller.component;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.ReviewDaoImpl;
import com.fiesta.model.vo.Review;
import com.fiesta.model.vo.Service;

public class ShowReviewController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String reviewCode = request.getParameter("reviewCode");
		System.out.println("reviewCode : "+reviewCode);
		Review review = new Review();
		
		review=ReviewDaoImpl.getInstance().showReview(reviewCode);
		request.setAttribute("review", review);
		System.out.println("review"+review);
		
		return new ModelAndView("review/answerReview.jsp");
	}

}

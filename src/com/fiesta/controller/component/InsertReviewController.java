package com.fiesta.controller.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;

public class InsertReviewController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = "review_fail.jsp";
		
		int reviewScore = Integer.parseInt(request.getParameter("reviewScore"));
		String reviewImg = request.getParameter("reviewImg");
		String reviewDesc = request.getParameter("reviewDesc");
		
		return new ModelAndView(path);
	}

}

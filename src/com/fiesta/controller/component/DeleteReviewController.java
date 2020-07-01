package com.fiesta.controller.component;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.RegisterDaoImpl;
import com.fiesta.model.dao.ReviewDaoImpl;

public class DeleteReviewController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String reviewCode = request.getParameter("reviewCode");
			
		try {
				ReviewDaoImpl.getInstance().deleteReview(reviewCode);

		} catch (SQLException e) {
			
		}	
		return new ModelAndView("customerMypage.do");
	}

}



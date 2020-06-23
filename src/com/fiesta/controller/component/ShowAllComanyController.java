package com.fiesta.controller.component;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.FiestaDaoImpl;
import com.fiesta.model.vo.Review;

public class ShowAllComanyController implements Controller{
	public ShowAllComanyController() {};
	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<Review> list = new ArrayList<>();
		list = FiestaDaoImpl.getInstance().showAllCompany();

		request.setAttribute("list", list);

		return new ModelAndView("/resource/js/company/companylist.jsp");
	}

}
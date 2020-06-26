package com.fiesta.controller.component;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.FiestaDaoImpl;
import com.fiesta.model.dao.CompanyDaoImpl;
import com.fiesta.model.dao.ReviewDaoImpl;
import com.fiesta.model.vo.Review;
import com.fiesta.model.vo.Service;

public class ServiceAllShowController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String companycode = request.getParameter("companycode");
		int comCode = Integer.parseInt(companycode);
		String path = "";

		ArrayList<Service> list = CompanyDaoImpl.getInstance().showAllService(companycode);
		ArrayList<Review> list2 = ReviewDaoImpl.getInstance().showAllReview(comCode);
		request.setAttribute("list", list);
		request.setAttribute("list2", list2);
		request.setAttribute("companycode", companycode);
		path = "./company/serviceAllShowResult.jsp";
		

		return new ModelAndView(path);
	}

}

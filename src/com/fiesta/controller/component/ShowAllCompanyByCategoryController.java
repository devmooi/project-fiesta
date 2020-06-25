package com.fiesta.controller.component;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.FiestaDaoImpl;
import com.fiesta.model.dao.CompanyDaoImpl;
import com.fiesta.model.vo.Review;

public class ShowAllCompanyByCategoryController implements Controller{
	public ShowAllCompanyByCategoryController() {};
	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int category = Integer.parseInt(request.getParameter("category"));
		ArrayList<Review> list = new ArrayList<>();
	
		list = CompanyDaoImpl.getInstance().showAllCompanyByCategory(category);
		
		request.setAttribute("list", list);
		request.setAttribute("category", category);

		return new ModelAndView("./company/companylist.jsp");
	}
}

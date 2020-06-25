package com.fiesta.controller.component;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.FiestaDaoImpl;
import com.fiesta.model.dao.CompanyDaoImpl;
import com.fiesta.model.vo.Review;

public class lookupCompanyController implements Controller{
	public lookupCompanyController() {};
	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int category=-1;
		if(!request.getParameter("category").equals("")) {
			category = Integer.parseInt(request.getParameter("category"));
		}
		String searchBy = request.getParameter("searchBy");
		String searchContent = request.getParameter("searchContent");
		ArrayList<Review> list = new ArrayList<>();
		
		if(category!=-1) {
			category = Integer.parseInt(request.getParameter("category"));
			list = CompanyDaoImpl.getInstance().lookupCompany(category, searchBy, searchContent);
		}else {
			list = CompanyDaoImpl.getInstance().lookupCompany(searchBy, searchContent);
		}
		request.setAttribute("list", list);
		request.setAttribute("category", category);
		request.setAttribute("searchBy", searchBy);
		request.setAttribute("searchContent", searchContent);

		return new ModelAndView("./company/companylist.jsp");
	}
}

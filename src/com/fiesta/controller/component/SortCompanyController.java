package com.fiesta.controller.component;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.FiestaDaoImpl;
import com.fiesta.model.dao.CompanyDaoImpl;
import com.fiesta.model.vo.Review;

public class SortCompanyController implements Controller{

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int category=-1;
		String searchBy = request.getParameter("searchBy");
		String searchContent = request.getParameter("searchContent");
		ArrayList<Review> list = new ArrayList<>();
		String sortBy = request.getParameter("sortBy");
		
		if(request.getParameter("category").equals("")&&searchContent.equals("")) {
			list = CompanyDaoImpl.getInstance().sortCompany(sortBy);
		}else if(!request.getParameter("category").equals("")&&searchContent.equals("")) {
			category=Integer.parseInt(request.getParameter("category"));
			list = CompanyDaoImpl.getInstance().sortCompany(category, sortBy);
		}else if(request.getParameter("category").equals("")&&!searchContent.equals("")) {
			list = CompanyDaoImpl.getInstance().sortCompany(searchBy, searchContent, sortBy);
		}else {
			category=Integer.parseInt(request.getParameter("category"));
			list = CompanyDaoImpl.getInstance().sortCompany(category, searchBy, searchContent, sortBy);
		}
		
		request.setAttribute("list", list);
		request.setAttribute("category", category);
		request.setAttribute("searchBy", searchBy);
		request.setAttribute("searchContent", searchContent);
		
		return new ModelAndView("./company/companylist.jsp");
	}

}

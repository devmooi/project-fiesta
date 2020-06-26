package com.fiesta.controller.component;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.FiestaDaoImpl;
import com.fiesta.model.vo.Review;

public class SortCompanyController implements Controller{

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int category=-1;
		if(!request.getParameter("category").equals("")) {
			category = Integer.parseInt(request.getParameter("category"));
		}
		String searchBy = request.getParameter("searchBy");
		String searchContent = request.getParameter("searchContent");
		ArrayList<Review> list = new ArrayList<>();
		String sortBy = request.getParameter("sortBy");
		System.out.println("category : "+category+", searchBy : "+searchBy+", searchContent"+searchContent);
		if(category==-1&&searchContent.equals("")) {
			System.out.println(1);
			list = FiestaDaoImpl.getInstance().sortCompany(sortBy);
		}else if(category!=-1&&searchContent.equals("")) {
			System.out.println(2);
			list = FiestaDaoImpl.getInstance().sortCompany(category, sortBy);
		}else if(category==-1&&!searchContent.equals("")) {
			System.out.println(3);
			list = FiestaDaoImpl.getInstance().sortCompany(searchBy, searchContent, sortBy);
		}else {
			System.out.println(4);
			list = FiestaDaoImpl.getInstance().sortCompany(category, searchBy, searchContent, sortBy);
		}
		
		request.setAttribute("list", list);
		request.setAttribute("category", category);
		request.setAttribute("searchBy", searchBy);
		request.setAttribute("searchContent", searchContent);
		
		return new ModelAndView("./company/companylist.jsp");
	}

}

package com.fiesta.controller.component;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.CompanyDaoImpl;
import com.fiesta.model.dao.ReviewDaoImpl;
import com.fiesta.model.vo.Service;

public class ShowServiceController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int companycode = Integer.parseInt(request.getParameter("companycode"));
		ArrayList<Service> list = new ArrayList<>();
		
		list = CompanyDaoImpl.getInstance().showAllServiceByCompany(companycode);
		
		request.setAttribute("list", list);
		
		return new ModelAndView("review/insertReview.jsp");
	}

}

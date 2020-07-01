package com.fiesta.controller.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.CompanyDaoImpl;
import com.fiesta.model.dao.CustomerDaoImpl;
import com.fiesta.model.dao.QuestionDaoImpl;
import com.fiesta.model.vo.Custorder;
import com.fiesta.model.vo.Service;

public class CustomerOrderFromController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String orderRevdate = request.getParameter("orderRevdate");
		String orderPlace = request.getParameter("orderPlace");
		String orderBudget = request.getParameter("orderBudget");
		String orderRequire = request.getParameter("orderRequire");
		
		//int companycode = Integer.parseInt(request.getParameter("companycode"));
		int serviceCode = Integer.parseInt(request.getParameter("serviceCode"));
		//System.out.println(companycode);
		String custEmail = "encore@gmail.com";   //나중에 세션값에서 받아오기
		
		String path = "";

		Custorder custorder = new Custorder(orderRevdate, orderPlace, orderBudget, orderRequire, custEmail, serviceCode);
		
		CustomerDaoImpl.getInstance().insertCustOrder(custorder);
		Service service = CompanyDaoImpl.getInstance().showService(serviceCode);
		
		request.setAttribute("custorder", custorder);
		request.setAttribute("service", service);
		
		path = "customerOrder/customerOrderShow.jsp";
		
		//response.sendRedirect("ServiceAllShow.do?companycode="+companycode);
		
		return new ModelAndView(path);
		//return null;
	}

}

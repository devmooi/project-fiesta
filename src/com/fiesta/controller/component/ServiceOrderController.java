package com.fiesta.controller.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.CompanyDaoImpl;
import com.fiesta.model.dao.QuestionDaoImpl;
import com.fiesta.model.vo.Service;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ServiceOrderController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int companycode = Integer.parseInt(request.getParameter("companycode"));
		int serviceCode = Integer.parseInt(request.getParameter("serviceCode"));
		
		Service service = CompanyDaoImpl.getInstance().showService(serviceCode);
		
		request.setAttribute("companycode", companycode);
		request.setAttribute("serviceCode", serviceCode);
		request.setAttribute("service", service);

		//response.sendRedirect("ServiceAllShow.do?companycode="+companycode);

		return new ModelAndView("customer/1_requestRevdate.jsp");
		//return null;
	}

}

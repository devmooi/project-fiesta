package com.fiesta.controller.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.FiestaDaoImpl;
import com.fiesta.model.dao.CompanyDaoImpl;
import com.fiesta.model.vo.Service;

public class ServiceDeleteController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int companycode = 1; //나중에 세션에서 회사코드값 받아올것
		int serviceCode = Integer.parseInt(request.getParameter("serviceCode"));
		
		String path = "";

		CompanyDaoImpl.getInstance().deleteService(serviceCode);
		
		path = "ServiceAllShow.do?companycode="+companycode;  
		
		response.sendRedirect("ServiceAllShow.do?companycode="+companycode);
		
		//return new ModelAndView(path);
		return null;
	}

}

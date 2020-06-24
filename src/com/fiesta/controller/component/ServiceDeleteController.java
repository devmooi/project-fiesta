package com.fiesta.controller.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.FiestaDaoImpl;
import com.fiesta.model.vo.Service;

public class ServiceDeleteController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int serviceCode = Integer.parseInt(request.getParameter("serviceCode"));
		
		String path = "";

		FiestaDaoImpl.getInstance().deleteService(serviceCode);
		
		path = "ServiceAllShow.do";  
		
		return new ModelAndView(path);
	}

}

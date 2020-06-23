package com.fiesta.controller.component;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.FiestaDaoImpl;
import com.fiesta.model.vo.Service;

public class ServiceAllShowController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int companycode = Integer.parseInt(request.getParameter("companycode"));
		String path = "";

		ArrayList<Service> list = FiestaDaoImpl.getInstance().showAllService(companycode);
		request.setAttribute("list", list);
		path = "ServiceAllResult.jsp";
		

		return new ModelAndView(path);
	}

}

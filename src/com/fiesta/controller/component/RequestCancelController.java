package com.fiesta.controller.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.CustomerDaoImpl;

public class RequestCancelController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int requestCode = Integer.parseInt(request.getParameter("requestCode"));
		System.out.println("######## 이거 안 찍히나요?" + requestCode);
		CustomerDaoImpl.getInstance().changeRequestCondition(requestCode);
		
		return null;
	}

}

package com.fiesta.controller.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.FiestaDaoImpl;
import com.fiesta.model.vo.Service;

public class ServiceRegisterController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String serviceName = request.getParameter("serviceName");
		String serviceDesc = request.getParameter("serviceDesc");
		String serviceImg = request.getParameter("serviceImg");
		String serviceTag = request.getParameter("serviceTag");
		
		String path = "";

		//Service VO 변경으로 인해 수정
		Service service  = new Service(serviceName, serviceDesc, serviceImg, serviceTag, "ent1@gmail.com");

		FiestaDaoImpl.getInstance().insertService(service);


		return new ModelAndView("serviceRegisterResult.jsp");
	}

}
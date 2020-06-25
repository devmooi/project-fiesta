package com.fiesta.controller.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;

public class QuestionViewController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String qCode = request.getParameter("qCode");
		
		String path = "";

		//Question qDetail = FiestaDaoImpl.getInstance(). showQuestion(qCode);
		
		//request.setAttribute("qDetail", qDetail);
		
		path = "questionView.jsp";
			

		return new ModelAndView(path);
	}

}

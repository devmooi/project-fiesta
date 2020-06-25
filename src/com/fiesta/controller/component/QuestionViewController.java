package com.fiesta.controller.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.FiestaDaoImpl;

public class QuestionViewController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String qTitle = request.getParameter("qCode");
		
		String path = "";

		FiestaDaoImpl.getInstance().insertQuestion(qCode);
		
		request.setAttribute("qTitle", qTitle);
		request.setAttribute("qDesc", qDesc);
		
		path = "questionView.jsp";
			

		return new ModelAndView(path);
	}

}
